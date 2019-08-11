package com.zhang.model;

/**
 * 模型层--评论表
 * @author 12443
 *
 */
public class Comment {

	private String userid;
	private String productid;
	private String datils;
	
	
	
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Comment(String userid, String productid, String datils) {
		super();
		this.userid = userid;
		this.productid = productid;
		this.datils = datils;
	}


	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getDatils() {
		return datils;
	}
	public void setDatils(String datils) {
		this.datils = datils;
	}


	@Override
	public String toString() {
		return "Comment [userid=" + userid + ", productid=" + productid + ", datils=" + datils + "]";
	}
	
	
}
