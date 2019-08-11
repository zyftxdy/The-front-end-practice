package com.zhang.serviceImpl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.ProductDao;
import com.zhang.service.ProductService;

/**
 * 业务逻辑层实现子类--商品信息操作
 * @author 12443
 *
 */
public class ProductServiceImpl implements ProductService {

	//创建数据库访问对象
	private ProductDao productDao=new ProductDao();
	
	//查询商品列表
	@Override
	public List<Map<String, Object>> findProducts(int currentPage,int perPageRecords,int Sorting,Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findProducts(currentPage,perPageRecords,Sorting,parameters);
	}

	//商品条数
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getRows(parameters);
	}

	//根据ID查询商品最低价格
	@Override
	public double getMinPrice(String productId) {
		// TODO Auto-generated method stub
		return productDao.getMinPrice(productId);
	}

	//根据ID查询商品最高价格
	@Override
	public double getMaxPrice(String productId) {
		// TODO Auto-generated method stub
		return productDao.getMaxPrice(productId);
	}

	//查询商品总库存
	@Override
	public int getNums(String productId) {
		// TODO Auto-generated method stub
		return productDao.getNums(productId);
	}

	//查询商品颜色列表
	@Override
	public List<String> getColour(String productId) {
		// TODO Auto-generated method stub
		return productDao.getColour(productId);
	}

	//查询商品鞋码列表
	@Override
	public List<String> getShot(String productid) {
		// TODO Auto-generated method stub
		return productDao.getShot(productid);
	}

	//查询商品尺码列表
	@Override
	public List<String> getClothing(String productid) {
		// TODO Auto-generated method stub
		return productDao.getClothing(productid);
	}

	//根据条件重新查询商品价格
	@Override
	public double getPriceByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getPriceByCd(productid, color, parameters);
	}

	//根据条件重新查询商品图片
	@Override
	public String getPictureByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getPictureByCd(productid, color, parameters);
	}

	//根据条件重新查询商品库存
	@Override
	public int getNumsByCd(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.getNumsByCd(productid, color, parameters);
	}

	// 根据条件查询商品个数
	@Override
	public int CheckCount(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.CheckCount(productid, color, parameters);
	}

	//查询商品详情ID
	@Override
	public String findDatilsId(String productid, String color, Object... parameters) {
		// TODO Auto-generated method stub
		return productDao.findDatilsId(productid, color, parameters);
	}

	//查询商品评论
	@Override
	public List<Map<String, Object>> findComment(int currentPage,int perPageRecords,String productid) {
		// TODO Auto-generated method stub
		return productDao.findComment(currentPage,perPageRecords,productid);
	}

	//查询商品评论总数
	@Override
	public int getCommentRows(String productId) {
		// TODO Auto-generated method stub
		return productDao.getCommentRows(productId);
	}

}
