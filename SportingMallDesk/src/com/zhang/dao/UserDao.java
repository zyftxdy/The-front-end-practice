package com.zhang.dao;

import java.util.List;

import com.zhang.model.Address;
import com.zhang.model.User;
import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�
 * @author 12443
 *
 */
public class UserDao {

	/**
	 * ����û�
	 * @param user
	 * @return
	 */
	public boolean addUsers(User user){
		String sql="insert into users (userid,username,password,email,telephone,question,answer,create_date) values"
				+ " (usersequences.nextval,?,?,?,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getTelephone(),user.getQuestion(),
								 user.getAnswer());
	}
	
	/**
	 * ��ѯ��Ա�û���Ϣ
	 * @param userid
	 * @return
	 */
	public User findUser(String userid){
		String sql="select username,email,telephone,question,answer from users where userid=?";
		return DBHelp.selectSqlByClassnameRow(sql, User.class, userid);
	}
	
	/**
	 * �޸Ļ�Ա�û���Ϣ
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user){
		String sql="update users set email=?,telephone=?,question=?,answer=?,update_date=sysdate where userid=?";
		return DBHelp.executeSQl(sql, user.getEmail(),user.getTelephone(),user.getQuestion(),user.getAnswer(),user.getUserid());
	}
	
	/**
	 * ��ѯ�û�ID
	 * @param username
	 * @return
	 */
	public String findUserId(String username){
		String sql="select userid from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * ��¼ʱУ���û�
	 * @param user
	 * @return
	 */
	public int checkUser(User user){
		String sql="select count(*) from users where username=? and password=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, user.getUsername(),user.getPassword()).toString());
	}
	
	/**
	 * ��¼ʱУ���Ա��Ȩ��
	 * @param userid
	 * @return
	 */
	public String checkStatus(String username){
		String sql="select status from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * ���ע��ʱ���û����Ƿ����
	 * @param username
	 * @return
	 */
	public int checkUserName(String username){
		String sql="select count(*) from users where username=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, username).toString());
	}
	
	/**
	 * �޸�����ʱ��ѯ�ܱ�����
	 * @param userid
	 * @return
	 */
	public String checkQuestion(String username){
		String sql="select question from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * �޸�����ʱ��ѯ�ܱ����Ƿ�һ��
	 * @param userid
	 * @return
	 */
	public String checkAnswer(String username){
		String sql="select answer from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * �޸Ļ�Ա������
	 * @param user
	 * @return
	 */
	public boolean updatePwd(User user){
		String sql="update users set password=?,update_date=sysdate where username=?";
		return DBHelp.executeSQl(sql, user.getPassword(),user.getUsername());
	}
	
	/**
	 * �����û�ID����ջ���ַ
	 * @param address
	 * @param userid
	 * @return
	 */
	public boolean addAddress(Address address,String userid){
		String sql="insert into address (addressid,userid,addressname,province,city,addressdatils,telephone,create_date)"
				+ " values(addresssequences.nextval,?,?,?,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql, userid,address.getAddressname(),address.getProvince(),
				address.getCity(),address.getAddressdatils(),address.getTelephone());
	}
	
	/**
	 * ���ݻ�Ա�û�ID��ѯ�ջ���ַ
	 * @param userid
	 * @return
	 */
	public List<Address> findAddress(String userid){
		String sql="select addressid,addressname,province,city,addressdatils,telephone from address where userid=?";
		return DBHelp.selectSqlByClassname(sql, Address.class, userid);
	}
	
	/**
	 * �����ջ���ַIDɾ��
	 * @param addressid
	 * @return
	 */
	public boolean deleteAddress(String addressid){
		String sql="delete from address where addressid=?";
		return DBHelp.executeSQl(sql, addressid);
	}
	
	/**
	 * ���ݵ�ַID��ѯ�ջ���ַ
	 * @param addressid
	 * @return
	 */
	public Address findAddressByCd(String addressid){
		String sql="select addressid,addressname,province,city,addressdatils,telephone from address where addressid=?";
		return DBHelp.selectSqlByClassnameRow(sql, Address.class, addressid);
	}
	
	/**
	 * ���ݵ�ַID�޸��ջ���ַ
	 * @param address
	 * @return
	 */
	public boolean updateAddress(Address address){
		String sql="update address set addressname=?,province=?,city=?,addressdatils=?,telephone=?,update_date=sysdate where addressid=?";
		return DBHelp.executeSQl(sql, address.getAddressname(),address.getProvince(),address.getCity(),
								address.getAddressdatils(),address.getTelephone(),address.getAddressid());
	}
}
