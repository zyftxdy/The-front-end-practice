package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * ҵ���߼���--��Ա�û�����
 * @author 12443
 *
 */
public interface UserService {

	//��ҳ��ѯ��Ա�û���Ϣ
	public List<Map<String,Object>> findUsers(int currentPage, int perPageRecords, Object... parameters);
	//�޸Ļ�Ա�û�����Ȩ��
	public boolean updateStatus(String userid);
	//�����Ա�û���¼
	public boolean recoverStatus(String userid);
	//��ѯ�ܼ�¼��
	public int getRows(Object... parameters);
}
