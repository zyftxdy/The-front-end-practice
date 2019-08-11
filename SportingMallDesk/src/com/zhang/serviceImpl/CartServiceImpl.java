package com.zhang.serviceImpl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.CartDao;
import com.zhang.model.Tb_cart;
import com.zhang.service.CartService;

/**
 * 业务逻辑层实现子类--购物车操作
 * @author 12443
 *
 */
public class CartServiceImpl implements CartService {

	
	//创建数据库访问层对象
	private CartDao cartDao=new CartDao();
	
	//添加商品进临时购物车
	@Override
	public boolean addCart(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.addCart(cart);
	}

	//修改商品数量
	@Override
	public boolean updateCart(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.updateCart(cart);
	}

	//查询临时购物车
	@Override
	public List<Map<String, Object>> findProducts(String userid) {
		// TODO Auto-generated method stub
		return cartDao.findProducts(userid);
	}
	
	//从临时购物车中查找已存在商品
	@Override
	public int getCount(Tb_cart cart) {
		// TODO Auto-generated method stub
		return cartDao.getCount(cart);
	}
	
	// 从临时购物车中删除商品信息
	@Override
	public boolean daleteCart(String id) {
		// TODO Auto-generated method stub
		return cartDao.daleteCart(id);
	}
	
	//从临时购物车中遍历批量删除的商品编号，删除商品
	@Override
	public boolean deleteCarts(String... parameters) {
		// TODO Auto-generated method stub
		return cartDao.deleteCarts(parameters);
	}

	//获取商品数量
	@Override
	public int getQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.getQuantity(id);
	}

	//增加商品
	@Override
	public boolean addQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.addQuantity(id);
	}

	//减少商品
	@Override
	public boolean reduceQuantity(String id) {
		// TODO Auto-generated method stub
		return cartDao.reduceQuantity(id);
	}

	//从临时购物车查询商品单价
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
