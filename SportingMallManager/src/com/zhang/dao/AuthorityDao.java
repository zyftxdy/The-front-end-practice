package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.model.AuthorityUser;
import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�--����ԱȨ�޲���
 * @author 12443
 *
 */
public class AuthorityDao {

	/**
	 * ���Ȩ����Ա
	 * @param au
	 * @return
	 */
	public boolean addAuthority(AuthorityUser au) {
		String sql="insert into authoritys (id,adminName,adminPwd,authority,create_date) "
				+ " values (authoritySequences.nextval,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql, au.getAdminName(),au.getAdminPwd(),au.getAuthority());
	}
	
	/**
	 * ����������ѯȨ���б�
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>>  findAuthority(int currentPage, int perPageRecords, Object... parameters){
		String sql="select m.id,m.adminname,m.adminPwd,m.authority,m.cd from "
				+ " (select rownum r,id,adminname,adminPwd,authority,"
				+ " to_char(create_date,'YYYY-MM-DD HH24:mi:ss') cd "
				+ " from authoritys where rownum<=("+(currentPage *perPageRecords )+") ";
		if(parameters!=null && parameters.length>0){
			//��Ȩ�޲�ѯ��Ϣ
			if(parameters[0]!=null){
				sql = sql + " and authority=" + parameters[0].toString();
			}
		}
		sql = sql + ") m  where m.r>" + (currentPage -1) * perPageRecords +" order by id asc ";
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * ��ѯ����ܼ�¼��
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql ="select count(*) from authoritys";
		if(parameters!=null && parameters.length>0){
			//��Ȩ�޲�ѯ��Ϣ
			if(parameters[0]!=null){
				sql=sql+" where authority="+parameters[0].toString();
			}
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());		
	}
	
	/**
	 * �����û����õ�Ȩ����
	 * @param adminName
	 * @return
	 */
	public int getAuthority(String adminName) {
		String sql="select authority from authoritys where adminName=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, adminName).toString());
	}
	
	/**
	 * ��֤�û�
	 * @param au
	 * @return
	 */
	public int checkUser(AuthorityUser au) {
		String sql="select count(*) from authoritys where adminName=? and adminPwd=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, au.getAdminName(),au.getAdminPwd()).toString());
	}
	
	/**
	 * ɾ��Ȩ���û�
	 * @param authorityId
	 * @return
	 */
	public boolean daleteAuthority(int authorityId){
		String sql="delete from authoritys where id=?";
		return DBHelp.executeSQl(sql, authorityId);
	}
	
	/**
	 * �޸�Ȩ��
	 * @param authorityId
	 * @param authority
	 * @return
	 */
	public boolean updateAuthority(int authorityId,int authority) {
		String sql="update authoritys set authority=? where id=?";
		return DBHelp.executeSQl(sql, authority,authorityId);
	}
}
