package com.zhang.service;

import java.util.List;

import com.zhang.model.Address;
import com.zhang.model.User;

/**
 * 业务逻辑层--用户信息操作
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public boolean addUsers(User user);
	
	/**
	 * 查询用户ID
	 * @param username
	 * @return
	 */
	public String findUserId(String username);
	
	/**
	 * 查询会员用户信息
	 * @param userid
	 * @return
	 */
	public User findUser(String userid);
	
	/**
	 * 修改会员用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * 登录时校验用户
	 * @param user
	 * @return
	 */
	public int checkUser(User user);
	
	/**
	 * 登录时校验用户权限
	 * @param userid
	 * @return
	 */
	public String checkStatus(String username);
	
	/**
	 * 检查注册时的用户名是否存在
	 * @param username
	 * @return
	 */
	public int checkUserName(String username);
	
	/**
	 * 修改密码时查询密保问题
	 * @param userid
	 * @return
	 */
	public String checkQueston(String username);
	
	/**
	 * 修改密码时查询密保答案是否一致
	 * @param userid
	 * @return
	 */
	public String checkAnswer(String username);
	
	/**
	 * 修改会员的密码
	 * @param user
	 * @return
	 */
	public boolean updatePwd(User user);
	
	/**
	 * 根据用户ID添加收货地址
	 * @param address
	 * @param userid
	 * @return
	 */
	public boolean addAddress(Address address,String userid);
	
	/**
	 * 根据会员用户ID查询收货地址
	 * @param userid
	 * @return
	 */
	public List<Address> findAddress(String userid);
	
	/**
	 * 根据收货地址ID删除
	 * @param addressid
	 * @return
	 */
	public boolean deleteAddress(String addressid);
	
	/**
	 * 根据地址ID查询收货地址
	 * @param addressid
	 * @return
	 */
	public Address findAddressByCd(String addressid);
	
	/**
	 * 根据地址ID修改收货地址
	 * @param address
	 * @return
	 */
	public boolean updateAddress(Address address);
}
