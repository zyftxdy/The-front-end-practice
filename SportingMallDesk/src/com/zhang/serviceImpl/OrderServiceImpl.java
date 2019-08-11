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
 * ҵ���߼���ʵ������--��������
 * @author 12443
 *
 */
public class OrderServiceImpl implements OrderService {

	//�������ݿ����ʶ���
	private OrderDao orderDao=new OrderDao();
	
	//��ѯѡ��Ҫ������Ʒ����Ϣ
	@Override
	public List<Map<String, Object>> findProdcut(String userid) {
		// TODO Auto-generated method stub
		return orderDao.findProdcut(userid);
	}

	//��ӽ�����
	@Override
	public boolean addOrder(Order order,Address address) {
		// TODO Auto-generated method stub
		return orderDao.addOrder(order,address);
	}

	//��ӽ���������
	@Override
	public boolean addOrderDatils(String orderNo,String  datilsid,String userid,String productname,int quantity){
		// TODO Auto-generated method stub
		return orderDao.addOrderDatils(orderNo, datilsid,userid,productname,quantity);
	}

	//�޸���Ʒ���
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

	//��ѯ������Ϣ
	@Override
	public List<Map<String, Object>> findOrder(int currentPage,int perPageRecords,String userid) {
		// TODO Auto-generated method stub
		return orderDao.findOrder(currentPage,perPageRecords,userid);
	}

	//��ѯ��������
	@Override
	public List<Map<String, Object>> findOrderDatils(String userid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderDatils(userid);
	}

	//���ݶ����Ų鿴�����ջ���ַ
	@Override
	public OrderAddress findOrderAddress(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderAddress(orderNo);
	}

	//���ݶ����Ų鿴������Ʒ����
	@Override
	public List<Map<String, Object>> findOrderDatilsByON(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderDatilsByON(orderNo);
	}

	//ȡ������(�޸Ķ���״̬Ϊ0)
	@Override
	public boolean cancelStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.cancelStatus(orderNo);
	}

	//֧������(�޸Ķ���״̬Ϊ2)
	@Override
	public boolean payStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.payStatus(orderNo);
	}

	//ȷ���ջ�(�޸Ķ���״̬Ϊ4)
	@Override
	public boolean getStatus(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.getStatus(orderNo);
	}

	//�鿴��ƷͼƬ������
	@Override
	public Product findById(String productid) {
		// TODO Auto-generated method stub
		return orderDao.findById(productid);
	}

	//�����Ʒ����
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
