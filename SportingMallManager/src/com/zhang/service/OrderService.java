package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.OrderAddress;

/**
 * ҵ���߼���--��������
 * @author 12443
 *
 */
public interface OrderService {

	/**
	 * ��ѯ����
	 * @param currentPage
	 * @param perPageRecords
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage, int perPageRecords,String orderNo);
	
	/**
	 * ��ѯ��������
	 * @param orderNo
	 * @return
	 */
	public int getRows(String orderNo);
	
	/**
	 * ���ݶ����Ų鿴�����ջ���ַ
	 * @param orderNo
	 * @return
	 */
	public OrderAddress findOrderAddress(String orderNo);
	
	/**
	 * ���ݶ����Ų鿴������Ʒ����
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatilsByON(String orderNo);
	
	/**
	 * ����(�޸Ķ���״̬Ϊ3)
	 * @param orderNo
	 * @return
	 */
	public boolean deliver(String orderNo);
}
