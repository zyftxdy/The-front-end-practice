package com.zhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zhang.model.ProductType;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层
 * @author Administrator
 *
 */
public class ProductTypeDao {

	/**
	 * 查询第一级分类
	 * @return
	 */
	public List<ProductType> findFirstType() {
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ProductType> list=new ArrayList<ProductType>();
		String sql="select firstTypeId,firstTypeName from FirstTypes";
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ProductType p=new ProductType();
				p.setTypeId(rs.getString(1));
				p.setTypeName(rs.getString(2));
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;
	}
	
	/**
	 * 根据分类名称查询分类ID
	 * @param firstTypeName
	 * @return
	 */
	public Object findByName(String firstTypeName){
		String sql="select firstTypeId from FirstTypes where firstTypeName=?";
		return DBHelp.getOneValue(sql, firstTypeName);
	}
	
	/**
	 * 根据一级分类ID查询分类名称
	 * @param firstTypeName
	 * @return
	 */
	public Object findById(String firstTypeId){
		String sql="select firstTypeName from FirstTypes where firstTypeId=?";
		return DBHelp.getOneValue(sql, firstTypeId);
	}
	
	/**
	 * 根据二级分类ID查询分类名称
	 * @param firstTypeName
	 * @return
	 */
	public Object findBySecondId(String secondTypeId){
		String sql="select secondTypeName from secondTypes where secondTypeId=?";
		return DBHelp.getOneValue(sql, secondTypeId);
	}
	
	/**
	 * 根据二级分类ID查询分类图片
	 * @param firstTypeName
	 * @return
	 */
	public Object findPicture(String secondTypeId){
		String sql="select picture from secondTypes where secondTypeId=?";
		return DBHelp.getOneValue(sql, secondTypeId);
	}
	
	/**
	 * 修改一级分类名称
	 * @param p
	 * @return
	 */
	public boolean updateFirstType(ProductType p){
		String sql="update FirstTypes set firstTypeName=?,update_date=sysdate where firstTypeId=?";
		return DBHelp.executeSQl(sql, p.getTypeName(),p.getTypeId());
	}
	
	/**
	 * 查询一级分类名称个数
	 * @param firstTypeName
	 * @return
	 */
	public  Object firstTypeName(String firstTypeName){
		String sql="select count(*) from Firsttypes where firstTypeName=?";
		return DBHelp.getOneValue(sql, firstTypeName);
	}
	
	
	/**
	 * 查询第二级分类
	 * @return
	 */
	public List<ProductType> findSecondType(String firstTypeId) {
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ProductType> list=new ArrayList<ProductType>();
		String sql="select secondTypeId,secondTypeName from SecondTypes where firstTypeId="+firstTypeId;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()){
				ProductType p=new ProductType();
				p.setTypeId(rs.getString(1));
				p.setTypeName(rs.getString(2));
				list.add(p);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;
	}
	
	/**
	 * 修改二级分类
	 * @param p
	 * @return
	 */
	public boolean updateSecondType(ProductType p){
		String sql="update SecondTypes set secondTypeName=?,picture=?,update_date=sysdate where secondTypeId=?";
		return DBHelp.executeSQl(sql, p.getTypeName(),p.getPicture(),p.getTypeId());
	}
	
	/**
	 * 删除第二品类
	 * @param secondTypeId
	 * @return
	 */
	public boolean deleteSecondType(String secondTypeId){
		String sql="delete from SecondTypes where secondTypeId=?";
		return DBHelp.executeSQl(sql, secondTypeId);
	}
	
	/**
	 * 查询二级分类名称
	 * @param firstTypeName
	 * @return
	 */
	public  Object secondTypeName(String secondTypeName){
		String sql="select count(*) from secondtypes where secondTypeName=?";
		return DBHelp.getOneValue(sql, secondTypeName);
	}	
	
	/**
	 * 添加新的二级分类
	 * @param secondTypeName
	 * @param firstTypeId
	 * @return
	 */
	public boolean addSecondTypes(String secondTypeName,String firstTypeId,String picture){
		String sql="insert into SecondTypes (secondTypeId,secondTypeName,firstTypeId,picture,create_date,update_date) values (secondTypesequences.nextval,?,?,?,sysdate,sysdate)";
		return DBHelp.executeSQl(sql, secondTypeName,firstTypeId,picture);
	}
}
