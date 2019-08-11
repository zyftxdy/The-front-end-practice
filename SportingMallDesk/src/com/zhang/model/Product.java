package com.zhang.model;

/**
 * 商品模型类
 * @author 12443
 *
 */
public class Product {

	private String productid;
	private String productname;
	private String productdesc;
	private String picture;
	
	
	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Product(String productid, String productname, String productdesc, String picture) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.productdesc = productdesc;
		this.picture = picture;
	}


	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getProductdesc() {
		return productdesc;
	}
	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}


	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", productdesc=" + productdesc
				+ ", picture=" + picture + "]";
	}
	
	
}
