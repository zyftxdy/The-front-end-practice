package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.util.DBHelp;

/**
 * 数据库访问层--评论操作
 * @author 12443
 *
 */
public class CommentDao {

	/**
	 * 查询商品评论
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,Object... parameters){
		String sql="select m.commentid,m.productid,m.username,m.commentdatils,m.cd from"
				+ " (select rownum r,c.commentid,c.productid,s.username,c.commentdatils,to_char(s.create_date,'YYYY-MM-DD HH24:MI:SS') cd "
				+ " from comments c,users s where c.userid=s.userid and "
				+ " rownum<=("+(currentPage*perPageRecords)+")";
		if(parameters!=null && parameters.length>0){
			//按商品编号查询商品信息
			if(parameters[0]!=null){
				sql = sql + " and c.commentid=" +parameters[0].toString();
			}
			//按商品名称查询商品
			if(parameters[1] != null){
				sql = sql + " and c.productid="+parameters[1].toString();
			}
		}
		sql=sql+") m where m.r>"+(currentPage -1) * perPageRecords;
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * 根据条件查询评论条数
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from comments";
		if(parameters!=null && parameters.length>0){
			//按商品编号查找商品
			if(parameters[0]!=null && parameters[1]==null){
				sql=sql+" where commentid="+parameters[0].toString();
			}
			//按商品名称查找商品
			if(parameters[1]!=null && parameters[0]==null){
				sql=sql+" where productid="+parameters[1].toString();
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());			
	}
	
	/**
	 * 根据评论ID删除商品评论
	 * @param commentid
	 * @return
	 */
	public boolean deleteComment(int commentid){
		String sql="delete from comments where commentid=?";
		return DBHelp.executeSQl(sql, commentid);
	}
}
