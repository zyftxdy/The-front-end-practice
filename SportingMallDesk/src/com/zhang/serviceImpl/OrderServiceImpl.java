package com.zhang.serviceImpl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.OrderDao;
import com.zhang.model.Address;
import com.zhang.model.Comment;
import com.zhang.model.Order;
import com.zhang.model.OrderAddress;
import com.zhang.model.Product;
import com.zhang.service.OrderService;

/**
 * 业务逻辑层实现子类--订单操作
 * @author 12443
 *
 */
public class OrderServiceImpl implements OrderService {

	//创建数据库层访问对象
	private OrderDao orderDao=new OrderDao();
	
	//查询选中要购买商品的信息
	@Override
	public List<Map<String, Object>> findProdcut(String userid) {
		// TODO Auto-generated method stub
		return orderDao.findProdcut(userid);
	}

	//添加进订单
	@Override
	public boolean addOrder(Order order,Address address) {
		// TODO Auto-generated method stub
		return orderDao.addOrder(order,address);
	}

	//添加进订单详情
	@Override
	public boolean addOrderDatils(String orderNo,String  datilsid,String userid,String productname,int quantity){
		// TODO Auto-generated method stub
		return orderDao.addOrderDatils(orderNo, datilsid,userid,productname,quantity);
	}

	//修改商品库存
	@Override
	public boolean updateNums(String datilsid, int quantity) {
		// TODO Auto-generated method stub
		return orderDao.updateNums(datilsid, quantity);
	}

	@Override
	public int getQuantity(String datilsid) {
		// TODO Auto-generated method stub
		return orderDao.getQuantity(datilsid);
	}

	@Override
	public boolean deleteCarts(String... parameters) {
		// TODO Auto-generated method stub
		return orderDao.deleteCarts(parameters);
	}

	//查询订单信息
	@Override
	public List<Map<String, Object>> findOrder(int currentPage,int perPageRecords,String userid) {
		// TODO Auto-generated method stub
		return orderDao.findOrder(currentPage,perPageRecords,userid);
	}

	//查询订单详情
	@Override
	public List<Map<String, Object>> findOrderDatils(String userid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderDatils(userid);
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

	//取消订单(修改订单状态为0)
	@Override
	public boolean cancelStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.cancelStatus(orderNo);
	}

	//支付订单(修改订单状态为2)
	@Override
	public boolean payStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.payStatus(orderNo);
	}

	//确认收货(修改订单状态为4)
	@Override
	public boolean getStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.getStatus(orderNo);
	}

	//查看商品图片和名称
	@Override
	public Product findById(String productid) {
		// TODO Auto-generated method stub
		return orderDao.findById(productid);
	}

	//添加商品评论
	@Override
	public boolean addComment(Comment comment) {
		// TODO Auto-generated method stub
		return orderDao.addComment(comment);
	}

	@Override
	public int getRows(String userid) {
		// TODO Auto-generated method stub
		return orderDao.getRows(userid);
	}

}
