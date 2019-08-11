package com.zhang.service;

import java.util.List;
import java.util.Map;

import com.zhang.model.AuthorityUser;

/**
 * ҵ���߼���--Ȩ�޲���
 * @author 12443
 *
 */
public interface AuthorityService {

	/**
	 * ���Ȩ����Ա
	 * @param au
	 * @return
	 */
	public boolean addAuthority(AuthorityUser au);
	
	/**
	 * ����������ѯȨ���б�
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>>  findAuthority(int currentPage, int perPageRecords, Object... parameters);
	
	/**
	 * ��ѯ����ܼ�¼��
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * �����û����õ�Ȩ����
	 * @param adminName
	 * @return
	 */
	public int getAuthority(String adminName);
	
	/**
	 * ��֤�û�
	 * @param au
	 * @return
	 */
	public int checkUser(AuthorityUser au);
	
	/**
	 * ɾ��Ȩ���û�
	 * @param authorityId
	 * @return
	 */
	public boolean daleteAuthority(int authorityId);
	
	/**
	 * �޸�Ȩ��
	 * @param authorityId
	 * @param authority
	 * @return
	 */
	public boolean updateAuthority(int authorityId,int authority);
}
