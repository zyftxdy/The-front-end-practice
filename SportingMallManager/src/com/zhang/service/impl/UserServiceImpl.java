package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.UserDao;
import com.zhang.model.User;
import com.zhang.service.UserService;

public class UserServiceImpl implements UserService{

	//�������ݿ����ʶ���
	private UserDao userDao=new UserDao();
	
	//��ҳ��ѯ��Ա�û���Ϣ
	@Override
	public List<Map<String, Object>> findUsers(int currentPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return userDao.findUsers(currentPage, perPageRecords, parameters);
	}

	//�����û�����Ȩ��
	@Override
	public boolean updateStatus(String userid) {
		// TODO Auto-generated method stub
		return userDao.updateStatus(userid);
	}

	//��ѯ�ܼ�¼��
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return userDao.getRows(parameters);
	}

	//�����Ա�û���¼
	@Override
	public boolean recoverStatus(String userid) {
		// TODO Auto-generated method stub
		return userDao.recoverStatus(userid);
	}

}
