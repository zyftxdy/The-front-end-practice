package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Address;
import com.zhang.model.Comment;
import com.zhang.model.Order;
import com.zhang.model.OrderAddress;
import com.zhang.model.Product;

/**
 * ҵ���߼��ӿ�--����
 * @author 12443
 *
 */
public interface OrderService {

	/**
	 * ��ѯѡ��Ҫ������Ʒ����Ϣ
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProdcut(String userid);
	
	/**
	 * ��Ӷ���
	 * @param order
	 * @return
	 */
	public boolean addOrder(Order order,Address address);
	
	/**
	 * �����Ʒ����������
	 * @param orderNo
	 * @param parameters
	 * @return
	 */
	public boolean addOrderDatils(String orderNo,String userid,String  datilsid,String productname,int quantity);
	
	/**
	 * ������Ʒ��Ų�����Ʒ����
	 * @param datilsid
	 * @return
	 */
	public int getQuantity(String datilsid);
	
	/**
	 * ����ʱ���ﳵ�б�������ɾ������Ʒ��ţ�ɾ����Ʒ
	 * @param parameters
	 * @return
	 */
	public boolean deleteCarts(String...parameters);
	
	/**
	 * �޸���Ʒ���
	 * @param datilsid
	 * @param quantity
	 * @return
	 */
	public boolean updateNums(String datilsid,int quantity);
	
	/**
	 * ��ѯ������Ϣ
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage,int perPageRecords,String userid);
	
	/**
	 * ��ѯ��������
	 * @param userid
	 * @return
	 */
	public int getRows(String userid);
	
	/**
	 * ��ѯ��������
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatils(String userid);
	
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
	 * ȡ������(�޸Ķ���״̬Ϊ0)
	 * @param orderNo
	 * @return
	 */
	public boolean cancelStatus(String orderNo);
	
	/**
	 * ֧������(�޸Ķ���״̬Ϊ2)
	 * @param orderNo
	 * @return
	 */
	public boolean payStatus(String orderNo);
	
	/**
	 * ȷ���ջ�(�޸Ķ���״̬Ϊ4)
	 * @param orderNo
	 * @return
	 */
	public boolean getStatus(String orderNo);
	
	/**
	 * ������ƷID������Ʒ���ƺ�ͼƬ
	 * @param productid
	 * @return
	 */
	public Product findById(String productid);
	
	/**
	 * �����Ʒ����
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment);
}
