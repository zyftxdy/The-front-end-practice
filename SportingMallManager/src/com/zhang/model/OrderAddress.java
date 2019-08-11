package com.zhang.model;

public class OrderAddress {

	private String orderno;
	private String create_date;
	private String addressname;
	private String province;
	private String city;		//所在城市
	private String addressdatils;//详细地址
	private String telephone;		//联系方式
	private double total;
	private int orderstatus;
	
	
	
	
	public OrderAddress() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderAddress(String orderno, String create_date, String addressname, String province, String city,
			String addressdatils, String telephone, double total, int orderstatus) {
		super();
		this.orderno = orderno;
		this.create_date = create_date;
		this.addressname = addressname;
		this.province = province;
		this.city = city;
		this.addressdatils = addressdatils;
		this.telephone = telephone;
		this.total = total;
		this.orderstatus = orderstatus;
	}
	
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getAddressname() {
		return addressname;
	}
	public void setAddressname(String addressname) {
		this.addressname = addressname;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddressdatils() {
		return addressdatils;
	}
	public void setAddressdatils(String addressdatils) {
		this.addressdatils = addressdatils;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public int getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}

	@Override
	public String toString() {
		return "OrderAddress [orderno=" + orderno + ", create_date=" + create_date + ", addressname=" + addressname
				+ ", province=" + province + ", city=" + city + ", addressdatils=" + addressdatils + ", telephone="
				+ telephone + ", total=" + total + ", orderstatus=" + orderstatus + "]";
	}
	
}
