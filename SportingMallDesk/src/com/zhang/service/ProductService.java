package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层--商品信息操作
 * @author Administrator
 *
 */
public interface ProductService {

	/**
	 * 查询商品列表
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProducts(int currentPage,int perPageRecords,int Sorting,Object... parameters);
	
	/**
	 * 查询商品条数
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * 根据商品ID查询商品最低价格
	 * @param productId
	 * @return
	 */
	public double getMinPrice(String productId);
	
	/**
	 * 根据商品ID查询商品最低价格
	 * @param productId
	 * @return
	 */
	public double getMaxPrice(String productId);
	
	/**
	 * 查询商品总库存
	 * @param productId
	 * @return
	 */
	public int getNums(String productId);
	
	/**
	 * 查询商品颜色列表
	 * @param productId
	 * @return
	 */
	public List<String> getColour(String productId);
	
	/**
	 * 查询商品鞋码列表
	 * @param productid
	 * @return
	 */
	public List<String> getShot(String productid);
	
	 /**
	  * 查询商品尺码列表
	 * @param productid
	 * @return
	 */
	public List<String> getClothing(String productid);
	
	/**
	 *根据条件查询商品的价格
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public double getPriceByCd(String productid,String color,Object...parameters);
	
	/**
	 *根据条件查询商品的图片
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public String getPictureByCd(String productid,String color,Object...parameters);
	
	/**
	 * 根据条件查询商品的剩余库存
	 * @param productid
	 * @param parameters
	 * @return
	 */
	public int getNumsByCd(String productid,String color,Object...parameters);
	
	/**
	 * 根据条件查询商品个数
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public int CheckCount(String productid,String color,Object...parameters);
	
	/**
	 * 查询商品详情ID
	 * @param datilsid
	 * @param userid
	 * @param productname
	 * @param number
	 * @return
	 */
	public String findDatilsId(String productid,String color,Object...parameters);
	
	/**
	 * 查询商品评论
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,String productid);
	
	/**
	 * 查询商品评论总数
	 * @param productId
	 * @return
	 */
	public int getCommentRows(String productId);
}
