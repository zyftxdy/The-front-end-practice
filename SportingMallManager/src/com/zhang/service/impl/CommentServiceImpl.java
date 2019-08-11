package com.zhang.service.impl;

import java.util.List;
import java.util.Map;

import com.zhang.dao.CommentDao;
import com.zhang.service.CommentService;

/**
 * 业务逻辑层实现子类--评论操作
 * @author 12443
 *
 */
public class CommentServiceImpl implements CommentService {

	//数据库层访问对象
	private CommentDao commentDao=new CommentDao();
	
	//根据条件查询评论
	@Override
	public List<Map<String, Object>> findComment(int currentPage, int perPageRecords, Object... parameters) {
		// TODO Auto-generated method stub
		return commentDao.findComment(currentPage, perPageRecords, parameters);
	}

	//根据条件查询评论条数
	@Override
	public int getRows(Object... parameters) {
		// TODO Auto-generated method stub
		return commentDao.getRows(parameters);
	}

	//根据评论ID删除评论
	@Override
	public boolean deleteComment(int commentid) {
		// TODO Auto-generated method stub
		return commentDao.deleteComment(commentid);
	}

}
