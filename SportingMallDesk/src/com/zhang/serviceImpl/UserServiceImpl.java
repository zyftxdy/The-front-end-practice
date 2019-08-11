package com.zhang.serviceImpl;

import java.util.List;

import com.zhang.dao.UserDao;
import com.zhang.model.Address;
import com.zhang.model.User;
import com.zhang.service.UserService;

/**
 * ҵ���߼���ʵ������--�û���Ϣ����
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {

	//�������ݿ���ʲ����
	private UserDao userDao=new UserDao();
	
	//��ӻ�Ա�û�
	@Override
	public boolean addUsers(User user) {
		// TODO Auto-generated method stub
		return userDao.addUsers(user);
	}

	//��¼ʱУ���û�
	@Override
	public int checkUser(User user) {
		// TODO Auto-generated method stub
		return userDao.checkUser(user);
	}

	//��¼ʱУ���û�Ȩ��
	@Override
	public String checkStatus(String username) {
		// TODO Auto-generated method stub
		return userDao.checkStatus(username);
	}
	
	//�����û����Ƿ��ظ�
	@Override
	public int checkUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.checkUserName(username);
	}

	//��ѯ�ܱ���
	@Override
	public String checkAnswer(String username) {
		// TODO Auto-generated method stub
		return userDao.checkAnswer(username);
	}

	//�޸�����
	@Override
	public boolean updatePwd(User user) {
		// TODO Auto-generated method stub
		return userDao.updatePwd(user);
	}

	//��ѯ�û�ID
	@Override
	public String findUserId(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserId(username);
	}

	//��ѯ�ܱ�����
	@Override
	public String checkQueston(String username) {
		// TODO Auto-generated method stub
		return userDao.checkQuestion(username);
	}

	//��ѯ��Ա�û���Ϣ
	@Override
	public User findUser(String userid) {
		// TODO Auto-generated method stub
		return userDao.findUser(userid);
	}

	//�޸Ļ�Ա�û���Ϣ
	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	//�����û�ID����ջ���ַ
	@Override
	public boolean addAddress(Address address, String userid) {
		// TODO Auto-generated method stub
		return userDao.addAddress(address, userid);
	}

	//���ݻ�Ա�û�ID��ѯ�ջ���ַ
	@Override
	public List<Address> findAddress(String userid) {
		// TODO Auto-generated method stub
		return userDao.findAddress(userid);
	}

	//�����ջ���ַIDɾ��
	@Override
	public boolean deleteAddress(String addressid) {
		// TODO Auto-generated method stub
		return userDao.deleteAddress(addressid);
	}

	//���ݵ�ַID��ѯ�ջ���ַ
	@Override
	public Address findAddressByCd(String addressid) {
		// TODO Auto-generated method stub
		return userDao.findAddressByCd(addressid);
	}

	//���ݵ�ַID�޸��ջ���ַ
	@Override
	public boolean updateAddress(Address address) {
		// TODO Auto-generated method stub
		return userDao.updateAddress(address);
	}

}
