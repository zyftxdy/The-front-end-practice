package com.zhang.service;

import java.util.List;

import com.zhang.model.ProductType;

/**
 * 业务逻辑层--商品信息操作
 * @author Administrator
 *
 */
public interface ProductTypeService {

	//查询一级分类
	public List<ProductType> findFirstType();
	//查询二级分类
	public List<ProductType> findSecondType(String firstTypeId);
	//修改一级分类名称
	public boolean updateFirstType(ProductType p);
	//修改二级分类名称
	public boolean updateSecondType(ProductType p);
	//查询一级分类名称个数
	public Object FirstTypeName(String firstTypeName);
	//查询二级分类名称个数
	public Object SecondTypeName(String secondTypeName);
	//添加新的二级分类名称
	public boolean addSecondTypes(String secondTypeName,String firstTypeId,String picture);
	//根据分类名称查询分类ID
	public Object findByName(String firstTypeName);
	//根据一级分类ID查询分类名称
	public Object findById(String firstTypeId);
	//根据二级分类ID查询分类名称
	public Object findBySecondId(String secondTypeId);
	//根据二级分类ID查询分类名称
	public Object findPicture(String secondTypeId);
	//删除第二品类
	public boolean deleteSecondType(String secondTypeId);
	
}
