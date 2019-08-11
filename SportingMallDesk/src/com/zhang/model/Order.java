package com.zhang.model;

/**
 * ģ�Ͳ�--������
 * @author 12443
 *
 */
public class Order {

	private String  orderid; //����ID
	private String  orderNo; //������
	private String addressid;//��ַID
	private String userId;   //�û�ID
	private String datilsId; //����ID
	private double total;    //��Ʒ�ܼ�
	
	
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String orderNo, String addressid, String userId, String datilsId, double total) {
		super();
		this.orderNo = orderNo;
		this.addressid = addressid;
		this.userId = userId;
		this.datilsId = datilsId;
		this.total = total;
	}
	public Order(String orderid, String orderNo, String addressid, String userId, String datilsId, double total) {
		super();
		this.orderid = orderid;
		this.orderNo = orderNo;
		this.addressid = addressid;
		this.userId = userId;
		this.datilsId = datilsId;
		this.total = total;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDatilsId() {
		return datilsId;
	}
	public void setDatilsId(String datilsId) {
		this.datilsId = datilsId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", orderNo=" + orderNo + ", addressid=" + addressid + ", userId=" + userId
				+ ", datilsId=" + datilsId + ", total=" + total + "]";
	}
	
	
}
