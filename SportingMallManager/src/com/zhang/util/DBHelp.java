package com.zhang.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

/**
 * 访问数据库操作的工具类
 * @author Administrator
 *
 */
public class DBHelp {

	//创建数据源对象
	private static DataSource ds;
	
	static{
		//解析dbcpconfig.properties属性文件中的name,value
		Properties ps = new Properties();
		
		try {
			//将属性文件加载到内存中
			ps.load(DBHelp.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			//获取数据源对象
			ds=BasicDataSourceFactory.createDataSource(ps);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 获取数据库连接对象
	 * @return
	 */
	public static Connection getConnection(){		
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 执行单表的DML语句操作:insert,update,delete
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static boolean executeSQl(String sql,Object... parameters){
		Connection connection = getConnection();
		PreparedStatement ps = null;		
		try {
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(sql);
			//设置参数值
			if(parameters!=null && parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					ps.setObject(i+1, parameters[i]);
				}
			}
			//执行sql语句
			int result =ps.executeUpdate();
			connection.commit();
            return result > 0 ? true:false;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			closeAll(null,ps,connection);
		}		
		return false;
	}
	
	/**
	 * 批量处理sql语句
	 * @param sqls(执行不同的sql语句)
	 * @return :true 执行成功  false:执行失败
	 */
	public static boolean executeBatchSql(String... sqls){
		Connection connection = getConnection();
		Statement st = null;		
		try {
			st = connection.createStatement();
			//设置手动提交事务
			connection.setAutoCommit(false);
			if(sqls!=null && sqls.length>0){
				for(String sql : sqls){
					st.addBatch(sql);
				}
				//执行批量处理
				st.executeBatch();
				//提交事务
				connection.commit();
				return true;
			}
		} catch (SQLException e) {
			//事务回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			closeAll(null,st,connection);
		}
		return false;
	}
	
	/**
	 * 执行查询操作,查询结果为单个值
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static Object getOneValue(String sql,Object... parameters){
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			if(parameters!=null && parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					//设置in参数的参数值
					ps.setObject(i+1, parameters[i]);
				}
			}
			//执行查询操作
			rs = ps.executeQuery();
			if(rs.next()){
				return rs.getObject(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(rs,ps,connection);
		}
		
		return null;
	}
	
	/**
	 * 执行查询操作,返回多行记录
	 * @param sql
	 * @param parameters
	 * @return
	 */
	public static List<Map<String,Object>> selectSqlRows(String sql,Object... parameters){
		//创建集合对象
		List<Map<String,Object>> results = new ArrayList<Map<String,Object>>();		
		Connection connection = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			//设置in参数值
			if(parameters!=null && parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					ps.setObject(i+1, parameters[i]);
				}
			}
			//实现查询操作
			rs = ps.executeQuery();
			//获取ResultSet对象中封装列的属性，类型
			ResultSetMetaData metaData = rs.getMetaData();
			//获取结果集的列数
			int columns = metaData.getColumnCount();
			//列名
			String column_name = "";
			Object column_value = null;
			while(rs.next()){
		      	Map<String,Object> row = new HashMap<String,Object>();
		      	for(int i=0;i<columns;i++){
		      		column_name = metaData.getColumnName(i+1);
		      		column_value = rs.getObject(column_name);
		      		row.put(column_name, column_value);
		      	}
		      	results.add(row);
			}
			return results;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(rs,ps,connection);
		}
		
		return null;
	}
	
	/**
	 * 根据Class对象，返回单条记录(修改)
	 * @param sql
	 * @param c Product.class,User.class
	 * @param parameters
	 * @return
	 */
	public static <T> T selectSqlByClassnameRow
	     (String sql,Class<T> c,Object... parameters){
		
		Connection connection = getConnection();
		PreparedStatement ps  = null;
		ResultSet rs = null;
		//获取当前类的实例化对象(反射机制)
		T obj =null;
		try {
			obj =c.newInstance();
			ps = connection.prepareStatement(sql);
			//设置参数值
			if(parameters!=null && parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					ps.setObject(i+1, parameters[i]);
				}
			}
			
			//执行查询操作
			rs = ps.executeQuery();
			//获取结果集中列的属性值
			ResultSetMetaData rm= rs.getMetaData();
			//获取结果集中的总列数
			int columns = rm.getColumnCount();
			String column_name = ""; //列名
			Object column_value = ""; //列值
			
			if(rs.next()){				
				//遍历每行每列值
				for(int i=0;i<columns;i++){
					//获取当前列
					column_name=rm.getColumnName(i+1);
					//获取该列值
					column_value = rs.getObject(column_name);
					
					//根据字段名获取Field对象
					Field field = c.getDeclaredField(column_name.toLowerCase());
					//设置该字段的属性值
					field.setAccessible(true);
					//判断当前列的数据类型
					if(rm.getColumnType(i+1)==java.sql.Types.NUMERIC){
						field.set(obj,Integer.parseInt(column_value.toString()));
					}else{
						//字符类型
						field.set(obj, column_value==null?"":column_value.toString());
					}
				}
					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBHelp.closeAll(rs, ps, connection);
		}
		
		return obj;
	}
	
	/**
	 * 根据Class对象，查询数据表记录
	 * @param sql
	 * @param c Product.class,User.class
	 * @param parameters
	 * @return
	 */
	public static <T> List<T> selectSqlByClassname
	     (String sql,Class<T> c,Object... parameters){
		List<T> rows = new ArrayList<T>();
		Connection connection = getConnection();
		PreparedStatement ps  = null;
		ResultSet rs = null;
		try {
			ps = connection.prepareStatement(sql);
			//设置参数值
			if(parameters!=null && parameters.length>0){
				for(int i=0;i<parameters.length;i++){
					ps.setObject(i+1, parameters[i]);
				}
			}
			
			//执行查询操作
			rs = ps.executeQuery();
			//获取结果集中列的属性值
			ResultSetMetaData rm= rs.getMetaData();
			//获取结果集中的总列数
			int columns = rm.getColumnCount();
			String column_name = ""; //列名
			Object column_value = ""; //列值
			while(rs.next()){
				//获取当前类的实例化对象(反射机制)
				T obj =c.newInstance();
				//遍历每行每列值
				for(int i=0;i<columns;i++){
					//获取当前列
					column_name=rm.getColumnName(i+1);
					//获取该列值
					column_value = rs.getObject(column_name);					
					//根据字段名获取Field对象
					Field field = c.getDeclaredField(column_name.toLowerCase());
					//设置该字段的属性值
					field.setAccessible(true);
					//判断当前列的数据类型
					if(rm.getColumnType(i+1)==java.sql.Types.NUMERIC){
						field.set(obj,Integer.parseInt(column_value.toString()));
					}else{
						//字符类型
						field.set(obj, column_value==null?"":column_value.toString());
					}
					
				}
				
				rows.add(obj);				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			DBHelp.closeAll(rs, ps, connection);
		}
		
		return rows;
	}
	
	/**
	 * 关闭数据库相关对象
	 * @param rs
	 * @param st
	 * @param conn
	 */
	public static void closeAll(ResultSet rs,Statement st,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		conn = null;
	}
}
