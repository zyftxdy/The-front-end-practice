package com.zhang.model;

/**
 * ģ�Ͳ�--��Ʒ����
 * @author 12443
 *
 */
public class ProductType {

	private String firsttypeid;//һ��ID
	private String firsttypename;//һ������
	private String secondtypeid;//����ID
	private String secondtypename;//�������
	private String picture;//���ͼƬ
	
	
	
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductType(String firsttypeid,String firsttypename, String secondtypeid, String secondtypename, String picture) {
		super();
		this.firsttypeid = firsttypeid;
		this.firsttypename=firsttypename;
		this.secondtypeid = secondtypeid;
		this.secondtypename = secondtypename;
		this.picture = picture;
	}
	
	
	
	public String getFirsttypename() {
		return firsttypename;
	}
	public void setFirsttypename(String firsttypename) {
		this.firsttypename = firsttypename;
	}
	public String getFirsttypeid() {
		return firsttypeid;
	}
	public void setFirsttypeid(String firsttypeid) {
		this.firsttypeid = firsttypeid;
	}
	public String getSecondtypeid() {
		return secondtypeid;
	}
	public void setSecondtypeid(String secondtypeid) {
		this.secondtypeid = secondtypeid;
	}
	public String getSecondtypename() {
		return secondtypename;
	}
	public void setSecondtypename(String secondtypename) {
		this.secondtypename = secondtypename;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	@Override
	public String toString() {
		return "ProductType [firsttypeid=" + firsttypeid + ", firsttypename=" + firsttypename + ", secondtypeid="
				+ secondtypeid + ", secondtypename=" + secondtypename + ", picture=" + picture + "]";
	}	
	
}
