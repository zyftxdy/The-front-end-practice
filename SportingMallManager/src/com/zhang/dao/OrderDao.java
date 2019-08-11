package com.zhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zhang.model.OrderAddress;
import com.zhang.util.DBHelp;

/**
 * ���ݿ���ʲ�--��������
 * @author 12443
 *
 */
public class OrderDao {

	/**
	 * ��ѯ����
	 * @param currentPage
	 * @param perPageRecords
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage, int perPageRecords,String orderNo){
		String sql="select m.orderid,m.orderno,m.addressname,m.cd,m.orderstatus,m.total from"
				+ " (select rownum r,s.orderid,s.orderno,s.addressname,"
				+ " to_char(s.create_date,'YYYY-MM-DD HH24:MI:SS') cd,"
				+ " s.orderstatus,s.total from orders s "
				+ " where rownum<=("+(currentPage *perPageRecords )+")";
			if(orderNo!=null && !"".equals(orderNo)){
				sql = sql + " and orderno=" +orderNo;
			}
		sql=sql+") m  where m.r>" + (currentPage -1) * perPageRecords +"";
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * ��ѯ��������
	 * @param orderNo
	 * @return
	 */
	public int getRows(String orderNo){
		String sql="select count(*) from orders";
		if(orderNo!=null && !"".equals(orderNo)){
			sql=sql+" where orderno="+orderNo;
		}
		return Integer.parseInt(DBHelp.getOneValue(sql,null).toString());
	}
	
	/**
	 * ���ݶ����Ų鿴�����ջ���ַ
	 * @param orderNo
	 * @return
	 */
	public OrderAddress findOrderAddress(String orderNo){
		Connection conn=DBHelp.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		OrderAddress orderAddress=new OrderAddress();
		String sql="select orderno,to_char(create_date,'YYYY-MM-DD HH24:MI:SS') create_date,addressname,"
				+ " province,city,addressdatils,orderstatus,total"
				+ " from orders where orderno=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs=ps.executeQuery();
			while(rs.next()){
				orderAddress.setOrderno(rs.getString(1));
				orderAddress.setCreate_date(rs.getString(2));
				orderAddress.setAddressname(rs.getString(3));
				orderAddress.setProvince(rs.getString(4));
				orderAddress.setCity(rs.getString(5));
				orderAddress.setAddressdatils(rs.getString(6));
				orderAddress.setOrderstatus(rs.getInt(7));
				orderAddress.setTotal(rs.getDouble(8));
			}
			return orderAddress;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBHelp.closeAll(rs, ps, conn);
		}
		return null;
	}
	
	/**
	 * ���ݶ����Ų鿴������Ʒ����
	 * @param orderNo
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatilsByON(String orderNo){
		String sql="select s.orderdatilsid,s.orderno,s.datilsid,s.productname,p.picture,"
				+ "p.productid,p.colour,p.shotsize,p.clothingsize,p.price,s.quantity "
				+ " from orderdatils s,product_datils p where s.datilsid=p.datilsid and s.orderno=?";
		return DBHelp.selectSqlRows(sql, orderNo);
	}
	
	/**
	 * ����(�޸Ķ���״̬Ϊ3)
	 * @param orderNo
	 * @return
	 */
	public boolean deliver(String orderNo){
		String sql="update orders set orderstatus=3,send_date=sysdate where orderNo=?";
		return DBHelp.executeSQl(sql, orderNo);
	}
	
}
