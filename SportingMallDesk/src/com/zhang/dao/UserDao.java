package com.zhang.dao;

import java.util.List;

import com.zhang.model.Address;
import com.zhang.model.User;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层
 * @author 12443
 *
 */
public class UserDao {

	/**
	 * 添加用户
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
	 * 查询会员用户信息
	 * @param userid
	 * @return
	 */
	public User findUser(String userid){
		String sql="select username,email,telephone,question,answer from users where userid=?";
		return DBHelp.selectSqlByClassnameRow(sql, User.class, userid);
	}
	
	/**
	 * 修改会员用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user){
		String sql="update users set email=?,telephone=?,question=?,answer=?,update_date=sysdate where userid=?";
		return DBHelp.executeSQl(sql, user.getEmail(),user.getTelephone(),user.getQuestion(),user.getAnswer(),user.getUserid());
	}
	
	/**
	 * 查询用户ID
	 * @param username
	 * @return
	 */
	public String findUserId(String username){
		String sql="select userid from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * 登录时校验用户
	 * @param user
	 * @return
	 */
	public int checkUser(User user){
		String sql="select count(*) from users where username=? and password=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, user.getUsername(),user.getPassword()).toString());
	}
	
	/**
	 * 登录时校验会员的权限
	 * @param userid
	 * @return
	 */
	public String checkStatus(String username){
		String sql="select status from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * 检查注册时的用户名是否存在
	 * @param username
	 * @return
	 */
	public int checkUserName(String username){
		String sql="select count(*) from users where username=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, username).toString());
	}
	
	/**
	 * 修改密码时查询密保问题
	 * @param userid
	 * @return
	 */
	public String checkQuestion(String username){
		String sql="select question from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * 修改密码时查询密保答案是否一致
	 * @param userid
	 * @return
	 */
	public String checkAnswer(String username){
		String sql="select answer from users where username=?";
		return DBHelp.getOneValue(sql, username).toString();
	}
	
	/**
	 * 修改会员的密码
	 * @param user
	 * @return
	 */
	public boolean updatePwd(User user){
		String sql="update users set password=?,update_date=sysdate where username=?";
		return DBHelp.executeSQl(sql, user.getPassword(),user.getUsername());
	}
	
	/**
	 * 根据用户ID添加收货地址
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
	 * 根据会员用户ID查询收货地址
	 * @param userid
	 * @return
	 */
	public List<Address> findAddress(String userid){
		String sql="select addressid,addressname,province,city,addressdatils,telephone from address where userid=?";
		return DBHelp.selectSqlByClassname(sql, Address.class, userid);
	}
	
	/**
	 * 根据收货地址ID删除
	 * @param addressid
	 * @return
	 */
	public boolean deleteAddress(String addressid){
		String sql="delete from address where addressid=?";
		return DBHelp.executeSQl(sql, addressid);
	}
	
	/**
	 * 根据地址ID查询收货地址
	 * @param addressid
	 * @return
	 */
	public Address findAddressByCd(String addressid){
		String sql="select addressid,addressname,province,city,addressdatils,telephone from address where addressid=?";
		return DBHelp.selectSqlByClassnameRow(sql, Address.class, addressid);
	}
	
	/**
	 * 根据地址ID修改收货地址
	 * @param address
	 * @return
	 */
	public boolean updateAddress(Address address){
		String sql="update address set addressname=?,province=?,city=?,addressdatils=?,telephone=?,update_date=sysdate where addressid=?";
		return DBHelp.executeSQl(sql, address.getAddressname(),address.getProvince(),address.getCity(),
								address.getAddressdatils(),address.getTelephone(),address.getAddressid());
	}
}
