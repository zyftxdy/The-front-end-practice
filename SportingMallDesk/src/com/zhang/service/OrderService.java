package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Address;
import com.zhang.model.Comment;
import com.zhang.model.Order;
import com.zhang.model.OrderAddress;
import com.zhang.model.Product;

/**
 * 业务逻辑接口--订单
 * @author 12443
 *
 */
public interface OrderService {

	/**
	 * 查询选中要购买商品的信息
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProdcut(String userid);
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	public boolean addOrder(Order order,Address address);
	
	/**
	 * 添加商品进订单详情
	 * @param orderNo
	 * @param parameters
	 * @return
	 */
	public boolean addOrderDatils(String orderNo,String userid,String  datilsid,String productname,int quantity);
	
	/**
	 * 根据商品编号查找商品数量
	 * @param datilsid
	 * @return
	 */
	public int getQuantity(String datilsid);
	
	/**
	 * 从临时购物车中遍历批量删除的商品编号，删除商品
	 * @param parameters
	 * @return
	 */
	public boolean deleteCarts(String...parameters);
	
	/**
	 * 修改商品库存
	 * @param datilsid
	 * @param quantity
	 * @return
	 */
	public boolean updateNums(String datilsid,int quantity);
	
	/**
	 * 查询订单信息
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage,int perPageRecords,String userid);
	
	/**
	 * 查询订单个数
	 * @param userid
	 * @return
	 */
	public int getRows(String userid);
	
	/**
	 * 查询订单详情
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatils(String userid);
	
	/**
	 * 根据订单号查看订单收货地址
	 * @param orderNo
	 * @return
	 */
	public OrderAddress findOrderAddress(String orderNo);
	
	/**
	 * 根据订单号查看订单商品详情
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatilsByON(String orderNo);
	
	/**
	 * 取消订单(修改订单状态为0)
	 * @param orderNo
	 * @return
	 */
	public boolean cancelStatus(String orderNo);
	
	/**
	 * 支付订单(修改订单状态为2)
	 * @param orderNo
	 * @return
	 */
	public boolean payStatus(String orderNo);
	
	/**
	 * 确认收货(修改订单状态为4)
	 * @param orderNo
	 * @return
	 */
	public boolean getStatus(String orderNo);
	
	/**
	 * 根据商品ID查找商品名称和图片
	 * @param productid
	 * @return
	 */
	public Product findById(String productid);
	
	/**
	 * 添加商品评论
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment);
}
