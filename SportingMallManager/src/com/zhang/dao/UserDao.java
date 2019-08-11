package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.model.User;
import com.zhang.util.DBHelp;

public class UserDao {

	/**
	 * 分页查询会员用户记录
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
			//按商品编号查询商品信息
			if(parameters[0]!=null){
				sql = sql + " and userid=" + parameters[0].toString();
			}
		}
		sql=sql+") m  where m.r>" + (currentPage -1) * perPageRecords +" order by userid asc";
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * 更改会员用户的权限状态
	 * @param user
	 * @return
	 */
	public boolean updateStatus(String userid){
		String sql="update users set status='异常' where userid=?";
		return DBHelp.executeSQl(sql,userid);
	}
	
	/**
	 * 允许会员用户登录
	 * @param user
	 * @return
	 */
	public boolean recoverStatus(String userid){
		String sql="update users set status='正常' where userid=?";
		return DBHelp.executeSQl(sql,userid);
	}
	
	/**
	 * 查询记录总数
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
