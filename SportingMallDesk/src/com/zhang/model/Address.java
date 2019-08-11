package com.zhang.model;

/**
 * 模型类--收货地址类
 * @author 12443
 *
 */
public class Address {

	private String addressid; //地址ID
	private String addressname; //收件人姓名
	private String province;	//所在省份
	private String city;		//所在城市
	private String addressdatils;//详细地址
	private String telephone;		//联系方式
	
	
	
	
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Address(String addressname, String province, String city, String addressdatils, String telephone) {
		super();
		this.addressname = addressname;
		this.province = province;
		this.city = city;
		this.addressdatils = addressdatils;
		this.telephone = telephone;
	}



	public Address(String addressid, String addressname, String province, String city, String addressdatils,
			String telephone) {
		super();
		this.addressid = addressid;
		this.addressname = addressname;
		this.province = province;
		this.city = city;
		this.addressdatils = addressdatils;
		this.telephone = telephone;
	}
	
	
	public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public String getAddressname() {
		return addressname;
	}
	public void setAddressname(String addressname) {
		this.addressname = addressname;
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



	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", addressname=" + addressname + ", province=" + province + ", city=" + city
				+ ", addressdatils=" + addressdatils + ", telephone=" + telephone + "]";
	}
	
	
}
