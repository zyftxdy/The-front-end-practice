package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.AuthorityDao;
import com.zhang.model.AuthorityUser;
import com.zhang.service.AuthorityService;

public class AuthorityServiceImpl implements AuthorityService {

	//创建数据库访问层对象
	private AuthorityDao authorityDao=new AuthorityDao();
	
	@Override
	public boolean addAuthority(AuthorityUser au) {
		// TODO Auto-generated method stub
		return authorityDao.addAuthority(au);
	}

	@Override
	public List<Map<String, Object>> findAuthority(int currentPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return authorityDao.findAuthority(currentPage, perPageRecords, parameters);
	}

	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return authorityDao.getRows(parameters);
	}

	@Override
	public int getAuthority(String adminName) {
		// TODO Auto-generated method stub
		return authorityDao.getAuthority(adminName);
	}

	@Override
	public int checkUser(AuthorityUser au) {
		// TODO Auto-generated method stub
		return authorityDao.checkUser(au);
	}

	@Override
	public boolean daleteAuthority(int authorityId) {
		// TODO Auto-generated method stub
		return authorityDao.daleteAuthority(authorityId);
	}

	@Override
	public boolean updateAuthority(int authorityId, int authority) {
		// TODO Auto-generated method stub
		return authorityDao.updateAuthority(authorityId, authority);
	}

}
