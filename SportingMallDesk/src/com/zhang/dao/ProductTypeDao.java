package com.zhang.dao;

import java.util.List;

import com.zhang.model.ProductType;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层--商品分类
 * @author 12443
 *
 */
public class ProductTypeDao {

	/**
	 * 查询一级分类
	 * @return
	 */
	public List<ProductType> findFirstType(){
		String sql="select firsttypeid,firsttypename from firstTypes";
		return DBHelp.selectSqlByClassname(sql, ProductType.class, null);
	}
	
	/**
	 * 查询二级分类
	 * @return
	 */
	public List<ProductType> findSecondType(){
		String sql="select firsttypeid,secondtypeid,secondtypename,picture from secondtypes";
		return DBHelp.selectSqlByClassname(sql, ProductType.class, null);
	}
}
