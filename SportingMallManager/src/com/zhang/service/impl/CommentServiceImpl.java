package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.CommentDao;
import com.zhang.service.CommentService;

/**
 * ҵ���߼���ʵ������--���۲���
 * @author 12443
 *
 */
public class CommentServiceImpl implements CommentService {

	//���ݿ����ʶ���
	private CommentDao commentDao=new CommentDao();
	
	//����������ѯ����
	@Override
	public List<Map<String, Object>> findComment(int currentPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return commentDao.findComment(currentPage, perPageRecords, parameters);
	}

	//����������ѯ��������
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return commentDao.getRows(parameters);
	}

	//��������IDɾ������
	@Override
	public boolean deleteComment(int commentid) {
		// TODO Auto-generated method stub
		return commentDao.deleteComment(commentid);
	}

}
