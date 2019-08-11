package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层--会员用户操作
 * @author 12443
 *
 */
public interface UserService {

	//分页查询会员用户信息
	public List<Map<String,Object>> findUsers(int currentPage, int perPageRecords, Object... parameters);
	//修改会员用户访问权限
	public boolean updateStatus(String userid);
	//允许会员用户登录
	public boolean recoverStatus(String userid);
	//查询总记录数
	public int getRows(Object... parameters);
}
