package com.zhang.dao;

import java.util.List;

import com.zhang.model.ProductType;
import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�--��Ʒ����
 * @author 12443
 *
 */
public class ProductTypeDao {

	/**
	 * ��ѯһ������
	 * @return
	 */
	public List<ProductType> findFirstType(){
		String sql="select firsttypeid,firsttypename from firstTypes";
		return DBHelp.selectSqlByClassname(sql, ProductType.class, null);
	}
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<ProductType> findSecondType(){
		String sql="select firsttypeid,secondtypeid,secondtypename,picture from secondtypes";
		return DBHelp.selectSqlByClassname(sql, ProductType.class, null);
	}
}
