package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Product;
import com.zhang.model.ProductDatils;

/**
 * 业务逻辑层--商品信息操作
 * @author Administrator
 *
 */
public interface ProductService {

	//添加商品
	public boolean addProduct(Product p);
	//添加商品详情
	public boolean addDatils(ProductDatils pd);
	//根据ID修改商品信息
	public boolean updateProduct(Product p);
	//根据ID修改商品详情
	public boolean updateDatils1(ProductDatils pd);
	//根据条件修改商品详情
	public boolean updateDatils(ProductDatils pd);
	//修改商品状态
	public boolean updateStatus(String productid);
	//恢复商品状态
	public boolean recoverStatus(String productid);
	//根据条件下架商品详情
	public boolean deleteDatils(String datilsId);
	//根据ID查询商品信息
	public Product findById(String productid);
	//按条件分页查询商品信息
	public List<Map<String,Object>> FindProducts(int currPage,int perPageRecords,Object... parameters);
	//查询表总记录数
	public int getRows(Object... parameters);
	//按条件分页查询商品详情
	public List<Map<String,Object>> FindDatils(int currPage,int perPageRecords,String productid,Object... parameters);
	//查询详情表的总记录数
	public int getDatilsRows(String productid,Object... parameters);
	//查询商品的颜色分类
	public int getColor(String productid);
	//查询商品的尺码分类
	public int getClothing(String productid);
	//查询商品的鞋码分类
	public int getShot(String productid);

}
