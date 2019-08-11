package com.zhang.model;

public class ProductDatils {

	private String productid; //商品ID
	private String color;     //商品颜色
	private String clothingsize;//商品尺寸
	private String shotsize;  //商品鞋码
	private double price;	  //商品价格
	private int nums;		  //商品库存
	private String picture;   //商品图片
	private String datilsId;
	
	
	
	public ProductDatils() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	public ProductDatils(String color, String clothingsize, String shotsize, double price, int nums, String picture) {
		super();
		this.color = color;
		this.clothingsize = clothingsize;
		this.shotsize = shotsize;
		this.price = price;
		this.nums = nums;
		this.picture = picture;
	}




	public ProductDatils(String productid, String color, String clothingsize, String shotsize, double price, int nums,
			String picture) {
		super();
		this.productid = productid;
		this.color = color;
		this.clothingsize = clothingsize;
		this.shotsize = shotsize;
		this.price = price;
		this.nums = nums;
		this.picture = picture;
	}


	


	public String getDatilsId() {
		return datilsId;
	}




	public void setDatilsId(String datilsId) {
		this.datilsId = datilsId;
	}




	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getClothingsize() {
		return clothingsize;
	}
	public void setClothingsize(String clothingsize) {
		this.clothingsize = clothingsize;
	}
	public String getShotsize() {
		return shotsize;
	}
	public void setShotsize(String shotsize) {
		this.shotsize = shotsize;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	
	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}




	@Override
	public String toString() {
		return "ProductDatils [productid=" + productid + ", color=" + color + ", clothingsize=" + clothingsize
				+ ", shotsize=" + shotsize + ", price=" + price + ", nums=" + nums + ", picture=" + picture
				+ ", datilsId=" + datilsId + "]";
	}




	
	
}
