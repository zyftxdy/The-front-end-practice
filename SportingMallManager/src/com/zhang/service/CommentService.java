package com.zhang.service;

import java.util.List;
import java.util.Map;

/**
 * 业务逻辑层--评论操作
 * @author 12443
 *
 */
public interface CommentService {

	/**
	 * 查询商品评论
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,Object... parameters);
	
	/**
	 * 根据条件查询评论条数
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters);
	
	/**
	 * 根据评论ID删除商品评论
	 * @param commentid
	 * @return
	 */
	public boolean deleteComment(int commentid);
}
