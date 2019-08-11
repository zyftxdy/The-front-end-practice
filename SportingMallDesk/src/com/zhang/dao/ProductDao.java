package com.zhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�--��Ʒ��
 * @author 12443
 *
 */
public class ProductDao {

	/**
	 * ��ѯ��Ʒ�б�
	 * @param currentPage
	 * @param perPageRecords
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProducts(int currentPage,int perPageRecords,int Sorting,Object... parameters){
		String sql="select s.productid,s.productname,s.productdesc,s.picture,s.price "
				+ " from (select rownum r,p.productid,p.productname,p.productdesc,p.picture,m.price"
				+ " from products p,(select productid,min(price) price from product_datils group by productid) m"
				+ " where p.productid=m.productid";
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and p.firsttypeid="+parameters[0];
			}
			if(parameters[1] != null){
				sql=sql+" and p.secondtypeid="+parameters[1];
			}
			if(parameters[2] != null){
				sql=sql+" and p.productname like '%" + parameters[2].toString()+"%'";
			}
		}
		sql=sql+" and p.productstatus='����' and rownum<=("+(currentPage*perPageRecords)+") ) s where s.r>"+ + (currentPage -1) * perPageRecords;
		if(Sorting == 2) {
			sql=sql+" order by s.price asc";
		}else if(Sorting == 3){
			sql=sql+" order by s.price desc";
		}
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * ��Ʒ�б�����
	 * @param parameters
	 * @return
	 */
	public int getRows(Object... parameters){
		String sql="select count(*) from "
				+ " (select p.productid,p.productname,p.productdesc,m.price from products p,(select productid,min(price) price "
				+ " from product_datils group by productid) m where p.productid=m.productid ";
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and p.firsttypeid="+parameters[0];
			}
			if(parameters[1] != null){
				sql=sql+" and p.secondtypeid="+parameters[1];
			}
			if(parameters[2] != null){
				sql=sql+" and p.productname like '%" + parameters[2].toString()+"%'";
			}
		}	
		sql=sql+" and p.productstatus='����') s";
		return Integer.parseInt(DBHelp.getOneValue(sql, null).toString());
	}
	
	/**
	 * ������ƷID��ѯ��Ʒ��ͼ۸�
	 * @param productId
	 * @return
	 */
	public double getMinPrice(String productId){
		String sql="select min(price) from product_datils where productid=?";
		return Double.parseDouble(DBHelp.getOneValue(sql, productId).toString());
	}
	
	/**
	 * ������ƷID��ѯ��Ʒ��ͼ۸�
	 * @param productId
	 * @return
	 */
	public double getMaxPrice(String productId){
		String sql="select max(price) from product_datils where productid=?";
		return Double.parseDouble(DBHelp.getOneValue(sql, productId).toString());
	}
	
	/**
	 * ��ѯ��Ʒ�ܿ��
	 * @param productId
	 * @return
	 */
	public int getNums(String productId){
		String sql="select sum(nums) from product_datils where productid=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, productId).toString());
	}
	
	/**
	 * ��ѯ��Ʒ��ɫ�б�
	 * @param productId
	 * @return
	 */
	public List<String> getColour(String productId){
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<String> list=new ArrayList<String>();
		String sql="select colour from product_datils where productid=? group by colour";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, productId);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;		
	}
	
	/**
	 * ��ѯ��ƷЬ���б�
	 * @param productid
	 * @return
	 */
	public List<String> getShot(String productid){
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<String> list=new ArrayList<String>();
		String sql="select shotsize from product_datils where productid=? group by shotsize";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, productid);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;		
	}

	/**
	 * ��ѯ��Ʒ�����б�
	 * @param productid
	 * @return
	 */
	public List<String> getClothing(String productid){
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<String> list=new ArrayList<String>();
		String sql="select clothingsize from product_datils where productid=? group by clothingsize";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, productid);
			rs=ps.executeQuery();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;
	}

	/**
	 *����������ѯ��Ʒ�ļ۸�
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public double getPriceByCd(String productid,String color,Object...parameters){
		String sql="select price from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and shotsize='"+parameters[0].toString()+"'";
			}
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
		}
		sql=sql+" and colour='"+color+"'";
		return Double.parseDouble(DBHelp.getOneValue(sql, null).toString());
	}

	/**
	 *����������ѯ��Ʒ��ͼƬ
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public String getPictureByCd(String productid,String color,Object...parameters){
		String sql="select picture from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and shotsize='"+parameters[0].toString()+"'";
			}
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
		}
		sql=sql+" and colour='"+color+"'";
		return DBHelp.getOneValue(sql, null).toString();
	}	
	
	/**
	 * ����������ѯ��Ʒ��ʣ����
	 * @param productid
	 * @param parameters
	 * @return
	 */
	public int getNumsByCd(String productid,String color,Object...parameters){
		String sql="select nums from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and shotsize='"+parameters[0].toString()+"'";
			}
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
		}
		sql=sql+" and colour='"+color+"'";
		return Integer.parseInt(DBHelp.getOneValue(sql, null).toString());
	}

	/**
	 * ����������ѯ��Ʒ����
	 * @param productid
	 * @param color
	 * @param parameters
	 * @return
	 */
	public int CheckCount(String productid,String color,Object...parameters){
		String sql="select count(*) from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and shotsize='"+parameters[0].toString()+"'";
			}
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
		}
		sql=sql+" and colour=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, color).toString());
	}
	
	
	/**
	 * ��ѯ��Ʒ����ID
	 * @param datilsid
	 * @param userid
	 * @param productname
	 * @param number
	 * @return
	 */
	public String findDatilsId(String productid,String color,Object...parameters){
		String sql="select datilsid from product_datils where productid="+productid;
		if(parameters!=null && parameters.length>0){
			if(parameters[0]!=null){
				sql=sql+" and shotsize='"+parameters[0].toString()+"'";
			}
			if(parameters[1]!=null){
				sql=sql+" and clothingsize='"+parameters[1].toString()+"'";
			}
		}
		sql=sql+" and colour=?";
		return DBHelp.getOneValue(sql, color).toString();
	}
	
	/**
	 * ��ѯ��Ʒ����
	 * @param productid
	 * @return
	 */
	public List<Map<String,Object>> findComment(int currentPage,int perPageRecords,String productid){
		String sql="select m.username,m.commentdatils,m.cd from"
				+ " (select rownum r, s.username,c.commentdatils,to_char(s.create_date,'YYYY-MM-DD HH24:MI:SS') cd "
				+ " from comments c,users s where c.userid=s.userid and "
				+ " rownum<=("+(currentPage*perPageRecords)+") and c.productid=?) m where m.r>"+(currentPage -1) * perPageRecords;
		return DBHelp.selectSqlRows(sql, productid);
	}
	
	/**
	 * ��ѯ��Ʒ��������
	 * @param productId
	 * @return
	 */
	public int getCommentRows(String productId) {
		String sql="select count(*) from comments where productId=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, productId).toString());
		
	}
}
