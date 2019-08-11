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
 * ���ݿ���ʲ�
 * @author Administrator
 *
 */
public class ProductTypeDao {

	/**
	 * ��ѯ��һ������
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
	 * ���ݷ������Ʋ�ѯ����ID
	 * @param firstTypeName
	 * @return
	 */
	public Object findByName(String firstTypeName){
		String sql="select firstTypeId from FirstTypes where firstTypeName=?";
		return DBHelp.getOneValue(sql, firstTypeName);
	}
	
	/**
	 * ����һ������ID��ѯ��������
	 * @param firstTypeName
	 * @return
	 */
	public Object findById(String firstTypeId){
		String sql="select firstTypeName from FirstTypes where firstTypeId=?";
		return DBHelp.getOneValue(sql, firstTypeId);
	}
	
	/**
	 * ���ݶ�������ID��ѯ��������
	 * @param firstTypeName
	 * @return
	 */
	public Object findBySecondId(String secondTypeId){
		String sql="select secondTypeName from secondTypes where secondTypeId=?";
		return DBHelp.getOneValue(sql, secondTypeId);
	}
	
	/**
	 * ���ݶ�������ID��ѯ����ͼƬ
	 * @param firstTypeName
	 * @return
	 */
	public Object findPicture(String secondTypeId){
		String sql="select picture from secondTypes where secondTypeId=?";
		return DBHelp.getOneValue(sql, secondTypeId);
	}
	
	/**
	 * �޸�һ����������
	 * @param p
	 * @return
	 */
	public boolean updateFirstType(ProductType p){
		String sql="update FirstTypes set firstTypeName=?,update_date=sysdate where firstTypeId=?";
		return DBHelp.executeSQl(sql, p.getTypeName(),p.getTypeId());
	}
	
	/**
	 * ��ѯһ���������Ƹ���
	 * @param firstTypeName
	 * @return
	 */
	public  Object firstTypeName(String firstTypeName){
		String sql="select count(*) from Firsttypes where firstTypeName=?";
		return DBHelp.getOneValue(sql, firstTypeName);
	}
	
	
	/**
	 * ��ѯ�ڶ�������
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
	 * �޸Ķ�������
	 * @param p
	 * @return
	 */
	public boolean updateSecondType(ProductType p){
		String sql="update SecondTypes set secondTypeName=?,picture=?,update_date=sysdate where secondTypeId=?";
		return DBHelp.executeSQl(sql, p.getTypeName(),p.getPicture(),p.getTypeId());
	}
	
	/**
	 * ɾ���ڶ�Ʒ��
	 * @param secondTypeId
	 * @return
	 */
	public boolean deleteSecondType(String secondTypeId){
		String sql="delete from SecondTypes where secondTypeId=?";
		return DBHelp.executeSQl(sql, secondTypeId);
	}
	
	/**
	 * ��ѯ������������
	 * @param firstTypeName
	 * @return
	 */
	public  Object secondTypeName(String secondTypeName){
		String sql="select count(*) from secondtypes where secondTypeName=?";
		return DBHelp.getOneValue(sql, secondTypeName);
	}	
	
	/**
	 * ����µĶ�������
	 * @param secondTypeName
	 * @param firstTypeId
	 * @return
	 */
	public boolean addSecondTypes(String secondTypeName,String firstTypeId,String picture){
		String sql="insert into SecondTypes (secondTypeId,secondTypeName,firstTypeId,picture,create_date,update_date) values (secondTypesequences.nextval,?,?,?,sysdate,sysdate)";
		return DBHelp.executeSQl(sql, secondTypeName,firstTypeId,picture);
	}
}
