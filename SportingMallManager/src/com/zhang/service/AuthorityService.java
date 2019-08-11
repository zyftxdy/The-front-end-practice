package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.AuthorityUser;

/**
 * 业务逻辑层--权限操作
 * @author 12443
 *
 */
public interface AuthorityService {

	/**
	 * 添加权限人员
	 * @param au
	 * @return
	 */
	public boolean addAuthority(AuthorityUser au);
	
	/**
	 * 根据条件查询权限列表
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>>  findAuthority(int currentPage, int perPageRecords, Object... parameters);
	
	/**
	 * 查询表的总记录数
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * 根据用户名得到权限名
	 * @param adminName
	 * @return
	 */
	public int getAuthority(String adminName);
	
	/**
	 * 验证用户
	 * @param au
	 * @return
	 */
	public int checkUser(AuthorityUser au);
	
	/**
	 * 删除权限用户
	 * @param authorityId
	 * @return
	 */
	public boolean daleteAuthority(int authorityId);
	
	/**
	 * 修改权限
	 * @param authorityId
	 * @param authority
	 * @return
	 */
	public boolean updateAuthority(int authorityId,int authority);
}
