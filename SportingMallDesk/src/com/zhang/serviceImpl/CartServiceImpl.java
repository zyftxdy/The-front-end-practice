package com.zhang.serviceImpl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.CartDao;
import com.zhang.model.Tb_cart;
import com.zhang.service.CartService;

/**
 * ҵ���߼���ʵ������--���ﳵ����
 * @author 12443
 *
 */
public class CartServiceImpl implements CartService {

	
	//�������ݿ���ʲ����
	private CartDao cartDao=new CartDao();
	
	//�����Ʒ����ʱ���ﳵ
	@Override
	public boolean addCart(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.addCart(cart);
	}

	//�޸���Ʒ����
	@Override
	public boolean updateCart(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.updateCart(cart);
	}

	//��ѯ��ʱ���ﳵ
	@Override
	public List<Map<String, Object>> findProducts(String userid) {
		// TODO Auto-generated method stub
		return cartDao.findProducts(userid);
	}
	
	//����ʱ���ﳵ�в����Ѵ�����Ʒ
	@Override
	public int getCount(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.getCount(cart);
	}
	
	// ����ʱ���ﳵ��ɾ����Ʒ��Ϣ
	@Override
	public boolean daleteCart(String id) {
		// TODO Auto-generated method stub
		return cartDao.daleteCart(id);
	}
	
	//����ʱ���ﳵ�б�������ɾ������Ʒ��ţ�ɾ����Ʒ
	@Override
	public boolean deleteCarts(String... parameters) {
		// TODO Auto-generated method stub
		return cartDao.deleteCarts(parameters);
	}

	//��ȡ��Ʒ����
	@Override
	public int getQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.getQuantity(id);
	}

	//������Ʒ
	@Override
	public boolean addQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.addQuantity(id);
	}

	//������Ʒ
	@Override
	public boolean reduceQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.reduceQuantity(id);
	}

	//����ʱ���ﳵ��ѯ��Ʒ����
	@Override
	public double findPrice(String id) {
		// TODO Auto-generated method stub
		return cartDao.findPrice(id);
	}

	@Override
	public boolean checked(String... parameters) {
		// TODO Auto-generated method stub
		return cartDao.checked(parameters);
	}

	@Override
	public boolean unchecked(String... parameters) {
		// TODO Auto-generated method stub
		return cartDao.unchecked(parameters);
	}

	@Override
	public String getProductName(String datilsid) {
		// TODO Auto-generated method stub
		return cartDao.getProductName(datilsid);
	}

}
