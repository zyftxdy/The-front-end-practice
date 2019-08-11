package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�--���۲���
 * @author 12443
 *
 */
public class CommentDao {

	/**
	 * ��ѯ��Ʒ����
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,Object... parameters){
		String sql="select m.commentid,m.productid,m.username,m.commentdatils,m.cd from"
				+ " (select rownum r,c.commentid,c.productid,s.username,c.commentdatils,to_char(s.create_date,'YYYY-MM-DD HH24:MI:SS') cd "
				+ " from comments c,users s where c.userid=s.userid and "
				+ " rownum<=("+(currentPage*perPageRecords)+")";
		if(parameters!=null && parameters.length>0){
			//����Ʒ��Ų�ѯ��Ʒ��Ϣ
			if(parameters[0]!=null){
				sql = sql + " and c.commentid=" +parameters[0].toString();
			}
			//����Ʒ���Ʋ�ѯ��Ʒ
			if(parameters[1] != null){
				sql = sql + " and c.productid="+parameters[1].toString();
			}
		}
		sql=sql+") m where m.r>"+(currentPage -1) * perPageRecords;
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * ����������ѯ��������
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from comments";
		if(parameters!=null && parameters.length>0){
			//����Ʒ��Ų�����Ʒ
			if(parameters[0]!=null && parameters[1]==null){
				sql=sql+" where commentid="+parameters[0].toString();
			}
			//����Ʒ���Ʋ�����Ʒ
			if(parameters[1]!=null && parameters[0]==null){
				sql=sql+" where productid="+parameters[1].toString();
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());			
	}
	
	/**
	 * ��������IDɾ����Ʒ����
	 * @param commentid
	 * @return
	 */
	public boolean deleteComment(int commentid){
		String sql="delete from comments where commentid=?";
		return DBHelp.executeSQl(sql, commentid);
	}
}
