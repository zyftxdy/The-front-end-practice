package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.model.User;
import com.zhang.util.DBHelp;

public class UserDao {

	/**
	 * ��ҳ��ѯ��Ա�û���¼
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findUsers(int currentPage, int perPageRecords, Object... parameters){
		String sql="select m.userid,m.username,m.telephone,m.cd,m.status"
				+ " from (select rownum r,userid,username,telephone,"
				+ "to_char(create_date,'YYYY-MM-DD HH24:mi:ss') cd,status from"
				+ " users where rownum<=("+(currentPage *perPageRecords )+")";
		if(parameters!=null && parameters.length>0){
			//����Ʒ��Ų�ѯ��Ʒ��Ϣ
			if(parameters[0]!=null){
				sql = sql + " and userid=" + parameters[0].toString();
			}
		}
		sql=sql+") m  where m.r>" + (currentPage -1) * perPageRecords +" order by userid asc";
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * ���Ļ�Ա�û���Ȩ��״̬
	 * @param user
	 * @return
	 */
	public boolean updateStatus(String userid){
		String sql="update users set status='�쳣' where userid=?";
		return DBHelp.executeSQl(sql,userid);
	}
	
	/**
	 * �����Ա�û���¼
	 * @param user
	 * @return
	 */
	public boolean recoverStatus(String userid){
		String sql="update users set status='����' where userid=?";
		return DBHelp.executeSQl(sql,userid);
	}
	
	/**
	 * ��ѯ��¼����
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from users";
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" where userid="+parameters[0].toString();
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());	
	}
}
