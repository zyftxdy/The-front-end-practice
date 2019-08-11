package com.zhang.service;

import java.util.List;

import com.zhang.model.Address;
import com.zhang.model.User;

/**
 * ҵ���߼���--�û���Ϣ����
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	public boolean addUsers(User user);
	
	/**
	 * ��ѯ�û�ID
	 * @param username
	 * @return
	 */
	public String findUserId(String username);
	
	/**
	 * ��ѯ��Ա�û���Ϣ
	 * @param userid
	 * @return
	 */
	public User findUser(String userid);
	
	/**
	 * �޸Ļ�Ա�û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	
	/**
	 * ��¼ʱУ���û�
	 * @param user
	 * @return
	 */
	public int checkUser(User user);
	
	/**
	 * ��¼ʱУ���û�Ȩ��
	 * @param userid
	 * @return
	 */
	public String checkStatus(String username);
	
	/**
	 * ���ע��ʱ���û����Ƿ����
	 * @param username
	 * @return
	 */
	public int checkUserName(String username);
	
	/**
	 * �޸�����ʱ��ѯ�ܱ�����
	 * @param userid
	 * @return
	 */
	public String checkQueston(String username);
	
	/**
	 * �޸�����ʱ��ѯ�ܱ����Ƿ�һ��
	 * @param userid
	 * @return
	 */
	public String checkAnswer(String username);
	
	/**
	 * �޸Ļ�Ա������
	 * @param user
	 * @return
	 */
	public boolean updatePwd(User user);
	
	/**
	 * �����û�ID����ջ���ַ
	 * @param address
	 * @param userid
	 * @return
	 */
	public boolean addAddress(Address address,String userid);
	
	/**
	 * ���ݻ�Ա�û�ID��ѯ�ջ���ַ
	 * @param userid
	 * @return
	 */
	public List<Address> findAddress(String userid);
	
	/**
	 * �����ջ���ַIDɾ��
	 * @param addressid
	 * @return
	 */
	public boolean deleteAddress(String addressid);
	
	/**
	 * ���ݵ�ַID��ѯ�ջ���ַ
	 * @param addressid
	 * @return
	 */
	public Address findAddressByCd(String addressid);
	
	/**
	 * ���ݵ�ַID�޸��ջ���ַ
	 * @param address
	 * @return
	 */
	public boolean updateAddress(Address address);
}
