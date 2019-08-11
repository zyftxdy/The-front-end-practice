package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Tb_cart;

/**
 * ҵ���߼���--���ﳵ
 * @author 12443
 *
 */
public interface CartService {

	/**
	 *  �����Ʒ����ʱ���ﳵ
	 * @param cart
	 * @return
	 */
	public boolean addCart(Tb_cart cart);
	
	/**
	 * �޸���Ʒ����
	 * @param cart
	 * @return
	 */
	public boolean updateCart(Tb_cart cart);
	
	/**
	 * �鿴��ʱ���ﳵ
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findProducts(String userid);
	
	/**
	 * ����ʱ���ﳵ�в����Ѵ�����Ʒ
	 * @param cart
	 * @return
	 */
	public int  getCount(Tb_cart cart);
	
	/**
	 * ��ȡ��ʱ���ﳵ����Ʒ������
	 * @param id
	 * @return
	 */
	public int getQuantity(String id);
	
	/**
	 * ����ʱ���ﳵ��ѯ��Ʒ����
	 * @param id
	 * @return
	 */
	public double findPrice(String id);
	
	/**
	 * ����ʱ���ﳵ�в�ѯ��Ʒ����
	 * @param datilsid
	 * @return
	 */
	public String getProductName(String datilsid);
	
	/**
	 * �����Ʒ
	 * @param id
	 * @return
	 */
	public boolean addQuantity(String id);
	
	/**
	 * ������Ʒ
	 * @param id
	 * @return
	 */
	public boolean reduceQuantity(String id);
	
	/**
	 * ����ʱ���ﳵ��ɾ����Ʒ��Ϣ
	 * @param id
	 * @return
	 */
	public boolean daleteCart(String id);
	
	/**
	 * ����ʱ���ﳵ�б�������ɾ������Ʒ��ţ�ɾ����Ʒ
	 * @param parameters
	 * @return
	 */
	public boolean deleteCarts(String...parameters);
	
	/**
	 * ����ʱ���ﳵ���޸�״̬Ϊѡ��
	 * @param parameters
	 * @return
	 */
	public boolean checked(String...parameters);
	
	/**
	 * ����ʱ���ﳵ���޸�״̬Ϊδѡ��
	 * @param parameters
	 * @return
	 */
	public boolean unchecked(String...parameters);
}
