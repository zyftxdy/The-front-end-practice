package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * ҵ���߼���--���۲���
 * @author 12443
 *
 */
public interface CommentService {

	/**
	 * ��ѯ��Ʒ����
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,Object... parameters);
	
	/**
	 * ����������ѯ��������
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * ��������IDɾ����Ʒ����
	 * @param commentid
	 * @return
	 */
	public boolean deleteComment(int commentid);
}
