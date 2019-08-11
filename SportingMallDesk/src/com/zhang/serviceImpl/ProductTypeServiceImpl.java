package com.zhang.serviceImpl;

import java.util.List;

import com.zhang.dao.ProductTypeDao;
import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;

/**
 * 业务逻辑层实现子类--商品分类信息操作
 * @author Administrator
 *
 */
public class ProductTypeServiceImpl implements ProductTypeService {

	//数据库访问层对象
	private ProductTypeDao ptd=new ProductTypeDao();
	
	//查询一级分类
	@Override
	public List<ProductType> findFirstType() {
		// TODO Auto-generated method stub
		return ptd.findFirstType();
	}

	//查询二级分类
	@Override
	public List<ProductType> findSecondType() {
		// TODO Auto-generated method stub
		return ptd.findSecondType();
	}

}
