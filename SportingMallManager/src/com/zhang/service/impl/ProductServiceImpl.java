package com.zhang.service.impl;



import java.util.List;
import java.util.Map;

import com.zhang.dao.ProductDao;
import com.zhang.model.Product;
import com.zhang.model.ProductDatils;
import com.zhang.service.ProductService;

/**
 * 业务逻辑层实现子类--商品操作
 * @author Administrator
 *
 */
public class ProductServiceImpl implements ProductService{

	//数据库访问层对象
	private ProductDao productDao=new ProductDao();
	
	//添加商品
	@Override
	public boolean addProduct(Product p) {
		// TODO Auto-generated method stub
		return productDao.addProduct(p);
	}
	
	//添加商品详情
	@Override
	public boolean addDatils(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.addProductDatils(pd);
	}
	
	//根据ID修改商品信息
	@Override
	public boolean updateProduct(Product p) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(p);
	}
	
	//修改商品状态
	@Override
	public boolean updateStatus(String productid) {
		// TODO Auto-generated method stub
		return productDao.updateStatus(productid);
	}
	
	//恢复商品状态
	@Override
	public boolean recoverStatus(String productid) {
		// TODO Auto-generated method stub
		return productDao.recoverStatus(productid);
	}
	
	//根据条件下架商品详情
	@Override
	public boolean deleteDatils(String datilsId) {
		// TODO Auto-generated method stub
		return productDao.deleteDatils(datilsId);
	}
	
	//根据ID查询商品信息
	@Override
	public Product findById(String productid) {
		// TODO Auto-generated method stub
		return productDao.findById(productid);
	}
	
	//按条件分页查询商品
	@Override
	public List<Map<String, Object>> FindProducts(int currPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.find(currPage, perPageRecords, parameters);
	}

	//获取表总记录数
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getRows(parameters);
	}

	//按条件分页查询商品详情
	@Override
	public List<Map<String, Object>> FindDatils(int currPage, int perPageRecords, String productid,
			Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findDatils(currPage, perPageRecords, productid, parameters);
	}

	//查询商品详情表的总记录数
	@Override
	public int getDatilsRows(String productid, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getDatilsRows(productid, parameters);
	}

	@Override
	public int getColor(String productid) {
		// TODO Auto-generated method stub
		return productDao.getColorRows(productid);
	}

	@Override
	public int getClothing(String productid) {
		// TODO Auto-generated method stub
		return productDao.getClothRows(productid);
	}

	@Override
	public int getShot(String productid) {
		// TODO Auto-generated method stub
		return productDao.getShotRows(productid);
	}

	//修改商品详情
	@Override
	public boolean updateDatils(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.updateDatils(pd);
	}

	@Override
	public boolean updateDatils1(ProductDatils pd) {
		// TODO Auto-generated method stub
		return productDao.updateDatils1(pd);
	}




}
