package com.zhang.dao;

import java.util.List;
import java.util.Map;

import com.zhang.model.Tb_cart;
import com.zhang.util.DBHelp;

/**
 * 数据库访问层--购物车
 * @author 12443
 *
 */
public class CartDao {

	/**
	 *  添加商品进临时购物车
	 * @param cart
	 * @return
	 */
	public boolean addCart(Tb_cart cart){
		String sql="insert into tb_cart (id,datilsid,userid,productname,quantity,create_date) values "
				+ " (tb_cartsequences.nextval,?,?,?,?,sysdate)";
		return DBHelp.executeSQl(sql, cart.getDatilsid(),cart.getUserid(),cart.getProductname(),cart.getNumber());
	}
	
	/**
	 * 修改商品数量
	 * @param cart
	 * @return
	 */
	public boolean updateCart(Tb_cart cart){
		String sql="update tb_cart set quantity=quantity+? where datilsid=? and userid=?";
		return DBHelp.executeSQl(sql, cart.getNumber(),cart.getDatilsid(),cart.getUserid());
	}
	
	/**
	 * 查看临时购物车
	 * @param userid
	 * @return
	 */
	public List<Map<String,Object>> findProducts(String userid){
		String sql="select t.id,t.productname,p.picture,t.datilsid,p.productid,p.colour,p.shotsize,"
				+ " p.clothingsize,p.price,t.quantity from product_datils p,tb_cart t "
				+ " where p.datilsid=t.datilsid and t.userid="+userid;
		return DBHelp.selectSqlRows(sql, null);
	}
	
	/**
	 * 查询商品单价
	 * @param id
	 * @return
	 */
	public double findPrice(String id){
		String sql="select p.price from (select datilsid from tb_cart where id=?) m,product_datils p where p.datilsid=m.datilsid";
		return Double.parseDouble(DBHelp.getOneValue(sql, id).toString());
	}
	
	/**
	 * 从临时购物车中查找已存在商品
	 * @param cart
	 * @return
	 */
	public int  getCount(Tb_cart cart){
		String sql="select count(*) from tb_cart where datilsid=? and userid=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, cart.getDatilsid(),cart.getUserid()).toString());
	}
	
	/**
	 * 获取临时购物车内商品的数量
	 * @param id
	 * @return
	 */
	public int getQuantity(String id){
		String sql="select quantity from tb_cart where id=?";
		return Integer.parseInt(DBHelp.getOneValue(sql, id).toString());
	}
	
	/**
	 * 从临时购物车中查询商品名称
	 * @param datilsid
	 * @return
	 */
	public String getProductName(String datilsid){
		String sql="select productname from tb_cart where datilsid=?";
		return DBHelp.getOneValue(sql, datilsid).toString();
		
	}
	
	/**
	 * 添加商品
	 * @param id
	 * @return
	 */
	public boolean addQuantity(String id){
		String sql="update tb_cart set quantity=quantity+1 where id=?";
		return DBHelp.executeSQl(sql, id);
	}
	
	/**
	 * 减少商品
	 * @param id
	 * @return
	 */
	public boolean reduceQuantity(String id){
		String sql="update tb_cart set quantity=quantity-1 where id=?";
		return DBHelp.executeSQl(sql, id);
	}
	
	/**
	 * 从临时购物车中删除商品信息
	 * @param id
	 * @return
	 */
	public boolean daleteCart(String id){
		String sql="delete from tb_cart where id=?";
		return DBHelp.executeSQl(sql, id);
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
				sql="delete from tb_cart where id="+parameters[i];
				sqls[i]=sql;
			}
			return DBHelp.executeBatchSql(sqls);
		}
		return false;
	}
	
	/**
	 * 从临时购物车中修改状态为选中
	 * @param parameters
	 * @return
	 */
	public boolean checked(String...parameters){
		
		if(parameters!=null && parameters.length>0){
			String sql="";
			String[] sqls=new String[parameters.length];
			for(int i=0;i<parameters.length;i++){
				sql="update tb_cart set checked=1 where id="+parameters[i];
				sqls[i]=sql;
			}
			return DBHelp.executeBatchSql(sqls);
		}
		return false;
	}
	
	/**
	 * 从临时购物车中修改状态为未选中
	 * @param parameters
	 * @return
	 */
	public boolean unchecked(String...parameters){
		
		if(parameters!=null && parameters.length>0){
			String sql="";
			String[] sqls=new String[parameters.length];
			for(int i=0;i<parameters.length;i++){
				sql="update tb_cart set checked=0 where id="+parameters[i];
				sqls[i]=sql;
			}
			return DBHelp.executeBatchSql(sqls);
		}
		return false;
	}
}
