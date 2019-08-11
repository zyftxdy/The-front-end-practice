package com.zhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zhang.model.Product;
import com.zhang.model.ProductDatils;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层
 * @author Administrator
 *
 */
public class ProductDao {

	/**
	 * 添加商品
	 * @param p
	 * @return
	 */
	public boolean addProduct(Product p){
		String sql="insert into products(productId,firstTypeId,secondTypeId,"
				+ "productName,productDesc,picture,create_date)"
				+ " values(productsequences.nextval,?,?,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql, p.getFirstTypeId(),p.getSecondTypeId(),p.getProductName(),
								p.getProductDesc(),p.getPicture());
	}
	
	/**
	 * 添加商品详情
	 * @param pd
	 * @return
	 */
	public boolean addProductDatils(ProductDatils pd){
		String sql="insert into product_datils (datilsid,productid,colour,clothingsize,shotsize,price,nums,picture,create_date)"
				+ " values(datilssequences.nextval,?,?,?,?,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql,pd.getProductid(),pd.getColor(),pd.getClothingsize(),pd.getShotsize(),
								 pd.getPrice(),pd.getNums(),pd.getPicture());
	}
	
	/**
	 * 修改商品信息
	 * @param p
	 * @return
	 */
	public boolean updateProduct(Product p){
		String sql="update products set productName=?,firstTypeId=?,secondTypeId=?,picture=?,productDesc=?,update_date=sysdate  where productid=?";
		return DBHelp.executeSQl(sql,p.getProductName(),p.getFirstTypeId(),p.getSecondTypeId(),p.getPicture(),p.getProductDesc(),p.getProductId());
	}
	
	/**
	 * 根据ID修改商品详情
	 * @param pd
	 * @return
	 */
	public boolean updateDatils1(ProductDatils pd){
		String sql="update product_datils set price=?,nums=?,picture=?,update_date=sysdate where productid=?";
		return DBHelp.executeSQl(sql,pd.getPrice(),pd.getNums(),pd.getPicture(),pd.getProductid());
	}
	
	/**
	 * 根据颜色修改商品详情
	 * @param pd
	 * @return
	 */
	public boolean updateDatils(ProductDatils pd){
		String sql="update product_datils set price=?,nums=?,picture=?,update_date=sysdate where datilsId=?";
		return DBHelp.executeSQl(sql,pd.getPrice(),pd.getNums(),pd.getPicture(),pd.getDatilsId());
	}
	
	/**
	 * 修改商品状态
	 * @param productid
	 * @return
	 */
	public boolean updateStatus(String productid){
		String sql="update products set productstatus='下架' where productid=?";
		return DBHelp.executeSQl(sql, productid);
	}
	
	/**
	 * 恢复商品状态
	 * @param productid
	 * @return
	 */
	public boolean recoverStatus(String productid){
		String sql="update products set productstatus='在售' where productid=?";
		return DBHelp.executeSQl(sql, productid);
	}
	
	/**
	 * 下架商品详情
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public boolean deleteDatils(String datilsId){
		String sql="delete from product_datils where datilsId="+datilsId;
		return DBHelp.executeSQl(sql, null);
		
	}
	
	/**
	 * 根据ID查询商品信息
	 * @param productid
	 * @return
	 */
	public Product findById(String productid){
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		Product p=null;
		String sql="select productName,productDesc,firstTypeId,secondTypeId,picture from products where productid=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, productid);
			rs=ps.executeQuery();
			while(rs.next()){
				p=new Product();
				p.setProductName(rs.getString(1));
				p.setProductDesc(rs.getString(2));
				p.setFirstTypeId(rs.getString(3));
				p.setSecondTypeId(rs.getString(4));
				p.setPicture(rs.getString(5));
			}
			return p;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;
	}

	/**
	 * 分页查询商品信息
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> find(int currentPage, int perPageRecords, Object... parameters) {
		String sql=" select m.productId,m.firstTypeId,m.secondTypeId,m.productName,m.cd,"
				+ "m.productStatus,m.productDesc,m.picture  from "
                + " (select rownum r, productId,firstTypeId,secondTypeId,productName,"
                + "to_char(create_date,'YYYY-MM-DD HH24:mi:ss') cd,productStatus,productDesc,picture "
                + "  from products "
                + "where rownum<=("+(currentPage *perPageRecords )+") ";
		if(parameters!=null && parameters.length>0){
			//按商品编号查询商品信息
			if(parameters[0]!=null){
				sql = sql + " and productId=" + parameters[0].toString();
			}
			//按商品名称查询商品
			if(parameters[1] != null){
				sql = sql + " and productName like '%" + parameters[1].toString()+"%'";
			}
		}
		sql = sql + ") m  where m.r>" + (currentPage -1) * perPageRecords +" order by productId asc";
		return DBHelp.selectSqlRows(sql, null);
		
	}
	
	/**
	 * 查询表的总记录数
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from products";
		if(parameters!=null && parameters.length>0){
			//按商品编号查找商品
			if(parameters[0]!=null && parameters[1]==null){
				sql=sql+" where productId="+parameters[0].toString();
			}
			//按商品名称查找商品
			if(parameters[1]!=null && parameters[0]==null){
				sql=sql+" where productName like '%"+parameters[1].toString()+"%'";
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * 分页查询商品详情
	 * @param currentPage
	 * @param perPageRecords
	 * @param productid
	 * @param parameters
	 * @return
	 */
    public List<Map<String,Object>> findDatils(int currentPage, int perPageRecords,String productid,Object...parameters){
    	String sql=" select m.datilsid,m.productid,m.colour,m.clothingsize,m.shotsize,m.cd,m.picture, "
				+ "  m.price,m.nums from "
                + " (select rownum r, datilsid,productid,colour,clothingsize,shotsize, "
                + " to_char(create_date,'YYYY-MM-DD HH24:mi:ss') cd,picture,price,nums "
                + "  from product_datils "
                + "where rownum<=("+(currentPage *perPageRecords )+") and productid="+productid;
    	if(parameters!=null && parameters.length>0){
			//按商品颜色查询商品信息
			if(parameters[0]!=null){
				sql = sql + " and colour='"+parameters[0].toString()+"'";
			}
			//按商品尺码查询商品
			if(parameters[1] != null){
				sql = sql + " and clothingsize='"+parameters[1].toString()+"'";
			}
			//按商品鞋码查询商品
			if(parameters[2] != null){
				sql = sql + " and shotsize='"+parameters[2].toString()+"'";
			}
		}
		sql = sql + ") m  where m.r>" + (currentPage -1) * perPageRecords +" ";
		return DBHelp.selectSqlRows(sql, null);
    }
    
	/**
	 * 查询详情表的总记录数
	 * @return
	 */
	public int getDatilsRows(String productid,Object... parameters){
		String sql ="select count(*) from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			//按商品颜色查找商品
			if(parameters[0]!=null){
				sql=sql+" and colour='"+parameters[0].toString()+"'";
			}
			//按商品尺码查找商品
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
			//按商品尺码查找商品
			if(parameters[2]!=null){
				sql=sql+" and shotsize='"+parameters[2].toString()+"'";
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * 查询详情表的颜色总数
	 * @return
	 */
	public int getColorRows(String productid){
		String sql ="select count(colour) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * 查询详情表的尺码总数
	 * @return
	 */
	public int getClothRows(String productid){
		String sql ="select count(clothingsize) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * 查询详情表的鞋码总数
	 * @return
	 */
	public int getShotRows(String productid){
		String sql ="select count(shotsize) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
}
