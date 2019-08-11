package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.UserDao;
import com.zhang.model.User;
import com.zhang.service.UserService;

public class UserServiceImpl implements UserService{

	//创建数据库层访问对象
	private UserDao userDao=new UserDao();
	
	//分页查询会员用户信息
	@Override
	public List<Map<String, Object>> findUsers(int currentPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return userDao.findUsers(currentPage, perPageRecords, parameters);
	}

	//更改用户访问权限
	@Override
	public boolean updateStatus(String userid) {
		// TODO Auto-generated method stub
		return userDao.updateStatus(userid);
	}

	//查询总记录数
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return userDao.getRows(parameters);
	}

	//允许会员用户登录
	@Override
	public boolean recoverStatus(String userid) {
		// TODO Auto-generated method stub
		return userDao.recoverStatus(userid);
	}

}
