package com.zhang.model;

public class Tb_cart {

	private String datilsid;//��Ʒ����ID
	private String userid;//�û�ID
	private String productname;//��Ʒ����
	private int number;//��Ʒ����
	
	
	
	public Tb_cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tb_cart(String datilsid, String userid, String productname, int number) {
		super();
		this.datilsid = datilsid;
		this.userid = userid;
		this.productname = productname;
		this.number = number;
	}
	public String getDatilsid() {
		return datilsid;
	}
	public void setDatilsid(String datilsid) {
		this.datilsid = datilsid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	@Override
	public String toString() {
		return "Tb_cart [datilsid=" + datilsid + ", userid=" + userid + ", productname=" + productname + ", number="
				+ number + "]";
	}
	
	
}
