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
 * ���ݿ���ʲ�
 * @author Administrator
 *
 */
public class ProductDao {

	/**
	 * �����Ʒ
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
	 * �����Ʒ����
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
	 * �޸���Ʒ��Ϣ
	 * @param p
	 * @return
	 */
	public boolean updateProduct(Product p){
		String sql="update products set productName=?,firstTypeId=?,secondTypeId=?,picture=?,productDesc=?,update_date=sysdate  where productid=?";
		return DBHelp.executeSQl(sql,p.getProductName(),p.getFirstTypeId(),p.getSecondTypeId(),p.getPicture(),p.getProductDesc(),p.getProductId());
	}
	
	/**
	 * ����ID�޸���Ʒ����
	 * @param pd
	 * @return
	 */
	public boolean updateDatils1(ProductDatils pd){
		String sql="update product_datils set price=?,nums=?,picture=?,update_date=sysdate where productid=?";
		return DBHelp.executeSQl(sql,pd.getPrice(),pd.getNums(),pd.getPicture(),pd.getProductid());
	}
	
	/**
	 * ������ɫ�޸���Ʒ����
	 * @param pd
	 * @return
	 */
	public boolean updateDatils(ProductDatils pd){
		String sql="update product_datils set price=?,nums=?,picture=?,update_date=sysdate where datilsId=?";
		return DBHelp.executeSQl(sql,pd.getPrice(),pd.getNums(),pd.getPicture(),pd.getDatilsId());
	}
	
	/**
	 * �޸���Ʒ״̬
	 * @param productid
	 * @return
	 */
	public boolean updateStatus(String productid){
		String sql="update products set productstatus='�¼�' where productid=?";
		return DBHelp.executeSQl(sql, productid);
	}
	
	/**
	 * �ָ���Ʒ״̬
	 * @param productid
	 * @return
	 */
	public boolean recoverStatus(String productid){
		String sql="update products set productstatus='����' where productid=?";
		return DBHelp.executeSQl(sql, productid);
	}
	
	/**
	 * �¼���Ʒ����
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
	 * ����ID��ѯ��Ʒ��Ϣ
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
	 * ��ҳ��ѯ��Ʒ��Ϣ
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
			//����Ʒ��Ų�ѯ��Ʒ��Ϣ
			if(parameters[0]!=null){
				sql = sql + " and productId=" + parameters[0].toString();
			}
			//����Ʒ���Ʋ�ѯ��Ʒ
			if(parameters[1] != null){
				sql = sql + " and productName like '%" + parameters[1].toString()+"%'";
			}
		}
		sql = sql + ") m  where m.r>" + (currentPage -1) * perPageRecords +" order by productId asc";
		return DBHelp.selectSqlRows(sql, null);
		
	}
	
	/**
	 * ��ѯ����ܼ�¼��
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from products";
		if(parameters!=null && parameters.length>0){
			//����Ʒ��Ų�����Ʒ
			if(parameters[0]!=null && parameters[1]==null){
				sql=sql+" where productId="+parameters[0].toString();
			}
			//����Ʒ���Ʋ�����Ʒ
			if(parameters[1]!=null && parameters[0]==null){
				sql=sql+" where productName like '%"+parameters[1].toString()+"%'";
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * ��ҳ��ѯ��Ʒ����
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
			//����Ʒ��ɫ��ѯ��Ʒ��Ϣ
			if(parameters[0]!=null){
				sql = sql + " and colour='"+parameters[0].toString()+"'";
			}
			//����Ʒ�����ѯ��Ʒ
			if(parameters[1] != null){
				sql = sql + " and clothingsize='"+parameters[1].toString()+"'";
			}
			//����ƷЬ���ѯ��Ʒ
			if(parameters[2] != null){
				sql = sql + " and shotsize='"+parameters[2].toString()+"'";
			}
		}
		sql = sql + ") m  where m.r>" + (currentPage -1) * perPageRecords +" ";
		return DBHelp.selectSqlRows(sql, null);
    }
    
	/**
	 * ��ѯ�������ܼ�¼��
	 * @return
	 */
	public int getDatilsRows(String productid,Object... parameters){
		String sql ="select count(*) from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			//����Ʒ��ɫ������Ʒ
			if(parameters[0]!=null){
				sql=sql+" and colour='"+parameters[0].toString()+"'";
			}
			//����Ʒ���������Ʒ
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
			//����Ʒ���������Ʒ
			if(parameters[2]!=null){
				sql=sql+" and shotsize='"+parameters[2].toString()+"'";
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * ��ѯ��������ɫ����
	 * @return
	 */
	public int getColorRows(String productid){
		String sql ="select count(colour) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * ��ѯ�����ĳ�������
	 * @return
	 */
	public int getClothRows(String productid){
		String sql ="select count(clothingsize) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * ��ѯ������Ь������
	 * @return
	 */
	public int getShotRows(String productid){
		String sql ="select count(shotsize) from product_datils where productid="+productid;
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
}
