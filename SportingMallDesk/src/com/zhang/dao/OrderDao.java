package com.zhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.zhang.model.Address;
import com.zhang.model.Comment;
import com.zhang.model.Order;
import com.zhang.model.OrderAddress;
import com.zhang.model.Product;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层--订单类
 * @author 12443
 *
 */
public class OrderDao {

	/**
	 * 查询选中要购买商品的信息
	 * @param parameters
	 * @return
	 */
	public List<Map<String,Object>> findProdcut(String userid){
		String sql="select t.id,t.productname,p.picture,t.datilsid,p.productid,p.colour,p.shotsize,"
				+ " p.clothingsize,p.price,t.quantity from product_datils p,tb_cart t "
				+ " where p.datilsid=t.datilsid and checked=1 and t.userid=?";
		return DBHelp.selectSqlRows(sql, userid);
	}
	
	/**
	 * 添加订单
	 * @param order
	 * @return
	 */
	public boolean addOrder(Order order,Address address){
		String sql="insert into orders (orderid,orderNo,userid,total,create_date,addressname,province,city,addressdatils,telephone)"
				+ " values (ordersequences.nextval,?,?,?,sysdate,?,?,?,?,?)";
		return DBHelp.executeSQl(sql, order.getOrderNo(),order.getUserId(),order.getTotal(),
				address.getAddressname(),address.getProvince(),address.getCity(),address.getAddressdatils(),address.getTelephone());
		
	}
	
	/**
	 * 添加商品进订单详情
	 * @param orderNo
	 * @param parameters
	 * @return
	 */
	public boolean addOrderDatils(String orderNo,String datilsid,String userid,String productname,int quantity){
		String sql="insert into orderdatils (orderdatilsid,orderNo,datilsid,productname,quantity,userid) "
				+ " values (orderdatilssequences.nextval,?,?,?,?,?)";
		return DBHelp.executeSQl(sql,orderNo,datilsid,productname,quantity,userid);

	}
	
	/**
	 * 修改商品库存
	 * @param datilsid
	 * @param quantity
	 * @return
	 */
	public boolean updateNums(String datilsid,int quantity){
		String sql="update product_datils set nums=nums-"+quantity+" where datilsid=?";
		return DBHelp.executeSQl(sql, datilsid);
	}
	
	/**
	 * 根据商品编号查找商品数量
	 * @param datilsid
	 * @return
	 */
	public int getQuantity(String datilsid){
		String sql="select quantity from tb_cart where datilsid=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, datilsid).toString());
	}
	
	/**
	 * 从临时购物车中遍历批量删除的商品编号，删除商品
	 * @param parameters
	 * @return
	 */
	public boolean deleteCarts(String...parameters){
		
		if(parameters!=null && parameters.length>0){
			String sql="";
			String[] sqls=new String[parameters.length];
			for(int i=0;i<parameters.length;i++){
				sql="delete from tb_cart where datilsid="+parameters[i];
				sqls[i]=sql;
			}
			return DBHelp.executeBatchSql(sqls);
		}
		return false;
	}
	
	/**
	 * 查询订单信息
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findOrder(int currentPage,int perPageRecords,String userid){
		String sql="select s.orderid,s.orderno,s.addressname,s.cd,s.orderstatus,s.total "
				+ " from (select rownum r,o.orderid,o.orderno,o.addressname,"
				+ " to_char(o.create_date,'YYYY-MM-DD HH24:MI:SS') cd,o.orderstatus,o.total "
				+ " from orders o where o.userid= "+userid
				+ " and rownum<=("+(currentPage*perPageRecords)+") ) s where s.r>"+ + (currentPage -1) * perPageRecords;
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * 查询订单个数
	 * @param userid
	 * @return
	 */
	public int getRows(String userid) {
		String sql="select count(*) from orders where userid=?"; 
		return Integer.parseInt(DBHelp.getOneValue(sql, userid).toString());
	}
	
	/**
	 * 查询订单详情
	 * @return
	 */
	public List<Map<String,Object>> findOrderDatils(String userid){
		String sql="select s.orderdatilsid,s.orderno,s.datilsid,s.productname,p.picture,"
				+ "p.productid,p.colour,p.shotsize,p.clothingsize,p.price,s.quantity "
				+ " from orderdatils s,product_datils p where s.datilsid=p.datilsid and s.userid=?";
		return DBHelp.selectSqlRows(sql, userid);
	}
	
	/**
	 * 根据订单号查看订单收货地址
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
	 * 根据订单号查看订单商品详情
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
	 * 取消订单(修改订单状态为0)
	 * @param orderNo
	 * @return
	 */
	public boolean cancelStatus(String orderNo){
		String sql="update orders set orderstatus=0,update_date=sysdate where orderno=?";
		return DBHelp.executeSQl(sql, orderNo);
	}
	
	/**
	 * 支付订单(修改订单状态为2)
	 * @param orderNo
	 * @return
	 */
	public boolean payStatus(String orderNo){
		String sql="update orders set orderstatus=2,pay_date=sysdate where orderno=?";
		return DBHelp.executeSQl(sql, orderNo);
	}
	
	/**
	 * 确认收货(修改订单状态为4)
	 * @param orderNo
	 * @return
	 */
	public boolean getStatus(String orderNo){
		String sql="update orders set orderstatus=4,get_date=sysdate where orderno=?";
		return DBHelp.executeSQl(sql, orderNo);
	}
	
	/**
	 * 根据商品ID查找商品名称和图片
	 * @param productid
	 * @return
	 */
	public Product findById(String productid){
		String sql="select productid,productname,picture from products where productid=?";
		return DBHelp.selectSqlByClassnameRow(sql, Product.class, productid);
	}
	
	/**
	 * 添加商品评论
	 * @param comment
	 * @return
	 */
	public boolean addComment(Comment comment){
		String sql="insert into comments (commentid,userid,productid,commentdatils,create_date) "
				+ " values(commentsequences.nextval,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql, comment.getUserid(),comment.getProductid(),comment.getDatils());
	}
}
