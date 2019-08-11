package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.OrderDao;
import com.zhang.model.OrderAddress;
import com.zhang.service.OrderService;

/**
 * 业务逻辑层实现子类--订单操作
 * @author 12443
 *
 */
public class OrderServiceImpl implements OrderService {

	//数据库访问层对象
	private OrderDao  orderDao=new OrderDao();
	
	//查询订单信息
	@Override
	public List<Map<String, Object>> findOrder(int currentPage, int perPageRecords, String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrder(currentPage, perPageRecords, orderNo);
	}

	//查询订单条数
	@Override
	public int getRows(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.getRows(orderNo);
	}

	//根据订单号查看订单收货地址
	@Override
	public OrderAddress findOrderAddress(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderAddress(orderNo);
	}

	//根据订单号查看订单商品详情
	@Override
	public List<Map<String, Object>> findOrderDatilsByON(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderDatilsByON(orderNo);
	}

	//发货(修改订单状态为3)
	@Override
	public boolean deliver(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.deliver(orderNo);
	}

}
