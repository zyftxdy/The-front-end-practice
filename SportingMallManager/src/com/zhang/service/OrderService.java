package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.OrderAddress;

/**
 * 业务逻辑层--订单操作
 * @author 12443
 *
 */
public interface OrderService {

	/**
	 * 查询订单
	 * @param currentPage
	 * @param perPageRecords
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage, int perPageRecords,String orderNo);
	
	/**
	 * 查询订单条数
	 * @param orderNo
	 * @return
	 */
	public int getRows(String orderNo);
	
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
	 * 发货(修改订单状态为3)
	 * @param orderNo
	 * @return
	 */
	public boolean deliver(String orderNo);
}
