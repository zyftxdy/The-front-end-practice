package com.zhang.service;

import java.util.List;

import com.zhang.model.ProductType;

/**
 * 业务逻辑层--商品分类信息操作
 * @author Administrator
 *
 */
public interface ProductTypeService {

	/**
	 * 查询一级分类
	 * @return
	 */
	public List<ProductType> findFirstType();
	
	/**
	 * 查询二级分类
	 * @return
	 */
	public List<ProductType> findSecondType();
}
