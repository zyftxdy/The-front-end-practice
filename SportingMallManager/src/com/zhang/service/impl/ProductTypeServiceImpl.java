package com.zhang.service.impl;

import java.util.List;

import com.zhang.dao.ProductTypeDao;
import com.zhang.model.ProductType;
import com.zhang.service.ProductTypeService;

/**
 * 业务逻辑层实现子类--商品操作
 * @author Administrator
 *
 */
public class ProductTypeServiceImpl implements ProductTypeService {

	//创建数据库访问层
	private ProductTypeDao productTypedao=new ProductTypeDao();
	
	//查询一级分类
	@Override
	public List<ProductType> findFirstType(){
		return productTypedao.findFirstType();
	}

	//查询二级分类
	@Override
	public List<ProductType> findSecondType(String firstTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findSecondType(firstTypeId);
	}
	
	//修改一级分类名称
	@Override
	public boolean updateFirstType(ProductType p) {
		// TODO Auto-generated method stub
		return productTypedao.updateFirstType(p);
	}

	//修改二级分类名称
	@Override
	public boolean updateSecondType(ProductType p) {
		// TODO Auto-generated method stub
		return productTypedao.updateSecondType(p);
	}
	
	//查询一级分类名称
	@Override
	public Object FirstTypeName(String firstTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.firstTypeName(firstTypeName);
	}

	//查询二级分类名称
	@Override
	public Object SecondTypeName(String secondTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.secondTypeName(secondTypeName);
	}

	//添加新的二级品类
	@Override
	public boolean addSecondTypes(String secondTypeName, String firstTypeId,String picture) {
		// TODO Auto-generated method stub
		return productTypedao.addSecondTypes(secondTypeName, firstTypeId,picture);
	}

	//根据分类名称查询分类ID
	@Override
	public Object findByName(String firstTypeName) {
		// TODO Auto-generated method stub
		return productTypedao.findByName(firstTypeName);
	}

	//删除第二品类
	@Override
	public boolean deleteSecondType(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.deleteSecondType(secondTypeId);
	}
	
	//根据一级分类ID查询分类名称
	@Override
	public Object findById(String firstTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findById(firstTypeId);
	}

	//根据二级分类ID查询分类名称
	@Override
	public Object findBySecondId(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findBySecondId(secondTypeId);
	}

	@Override
	public Object findPicture(String secondTypeId) {
		// TODO Auto-generated method stub
		return productTypedao.findPicture(secondTypeId);
	}
	
}
