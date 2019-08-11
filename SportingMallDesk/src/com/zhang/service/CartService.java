package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.Tb_cart;

/**
 * 业务逻辑层--购物车
 * @author 12443
 *
 */
public interface CartService {

	/**
	 *  添加商品进临时购物车
	 * @param cart
	 * @return
	 */
	public boolean addCart(Tb_cart cart);
	
	/**
	 * 修改商品数量
	 * @param cart
	 * @return
	 */
	public boolean updateCart(Tb_cart cart);
	
	/**
	 * 查看临时购物车
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findProducts(String userid);
	
	/**
	 * 从临时购物车中查找已存在商品
	 * @param cart
	 * @return
	 */
	public int  getCount(Tb_cart cart);
	
	/**
	 * 获取临时购物车内商品的数量
	 * @param id
	 * @return
	 */
	public int getQuantity(String id);
	
	/**
	 * 从临时购物车查询商品单价
	 * @param id
	 * @return
	 */
	public double findPrice(String id);
	
	/**
	 * 从临时购物车中查询商品名称
	 * @param datilsid
	 * @return
	 */
	public String getProductName(String datilsid);
	
	/**
	 * 添加商品
	 * @param id
	 * @return
	 */
	public boolean addQuantity(String id);
	
	/**
	 * 减少商品
	 * @param id
	 * @return
	 */
	public boolean reduceQuantity(String id);
	
	/**
	 * 从临时购物车中删除商品信息
	 * @param id
	 * @return
	 */
	public boolean daleteCart(String id);
	
	/**
	 * 从临时购物车中遍历批量删除的商品编号，删除商品
	 * @param parameters
	 * @return
	 */
	public boolean deleteCarts(String...parameters);
	
	/**
	 * 从临时购物车中修改状态为选中
	 * @param parameters
	 * @return
	 */
	public boolean checked(String...parameters);
	
	/**
	 * 从临时购物车中修改状态为未选中
	 * @param parameters
	 * @return
	 */
	public boolean unchecked(String...parameters);
}
