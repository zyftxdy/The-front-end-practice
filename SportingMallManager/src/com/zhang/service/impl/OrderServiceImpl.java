package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.OrderDao;
import com.zhang.model.OrderAddress;
import com.zhang.service.OrderService;

/**
 * ҵ���߼���ʵ������--��������
 * @author 12443
 *
 */
public class OrderServiceImpl implements OrderService {

	//���ݿ���ʲ����
	private OrderDao  orderDao=new OrderDao();
	
	//��ѯ������Ϣ
	@Override
	public List<Map<String, Object>> findOrder(int currentPage, int perPageRecords, String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrder(currentPage, perPageRecords, orderNo);
	}

	//��ѯ��������
	@Override
	public int getRows(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.getRows(orderNo);
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

	//����(�޸Ķ���״̬Ϊ3)
	@Override
	public boolean deliver(String orderNo) {
		// TODO Auto-generated method stub
		return orderDao.deliver(orderNo);
	}

}
