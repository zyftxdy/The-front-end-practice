package com.zhang.serviceImpl;

import java.util.List;

import com.zhang.dao.UserDao;
import com.zhang.model.Address;
import com.zhang.model.User;
import com.zhang.service.UserService;

/**
 * 业务逻辑层实现子类--用户信息操作
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {

	//创建数据库访问层对象
	private UserDao userDao=new UserDao();
	
	//添加会员用户
	@Override
	public boolean addUsers(User user) {
		// TODO Auto-generated method stub
		return userDao.addUsers(user);
	}

	//登录时校验用户
	@Override
	public int checkUser(User user) {
		// TODO Auto-generated method stub
		return userDao.checkUser(user);
	}

	//登录时校验用户权限
	@Override
	public String checkStatus(String username) {
		// TODO Auto-generated method stub
		return userDao.checkStatus(username);
	}
	
	//检验用户名是否重复
	@Override
	public int checkUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.checkUserName(username);
	}

	//查询密保答案
	@Override
	public String checkAnswer(String username) {
		// TODO Auto-generated method stub
		return userDao.checkAnswer(username);
	}

	//修改密码
	@Override
	public boolean updatePwd(User user) {
		// TODO Auto-generated method stub
		return userDao.updatePwd(user);
	}

	//查询用户ID
	@Override
	public String findUserId(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserId(username);
	}

	//查询密保问题
	@Override
	public String checkQueston(String username) {
		// TODO Auto-generated method stub
		return userDao.checkQuestion(username);
	}

	//查询会员用户信息
	@Override
	public User findUser(String userid) {
		// TODO Auto-generated method stub
		return userDao.findUser(userid);
	}

	//修改会员用户信息
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	//根据用户ID添加收货地址
	@Override
	public boolean addAddress(Address address, String userid) {
		// TODO Auto-generated method stub
		return userDao.addAddress(address, userid);
	}

	//根据会员用户ID查询收货地址
	@Override
	public List<Address> findAddress(String userid) {
		// TODO Auto-generated method stub
		return userDao.findAddress(userid);
	}

	//根据收货地址ID删除
	@Override
	public boolean deleteAddress(String addressid) {
		// TODO Auto-generated method stub
		return userDao.deleteAddress(addressid);
	}

	//根据地址ID查询收货地址
	@Override
	public Address findAddressByCd(String addressid) {
		// TODO Auto-generated method stub
		return userDao.findAddressByCd(addressid);
	}

	//根据地址ID修改收货地址
	@Override
	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		return userDao.updateAddress(address);
	}

}
