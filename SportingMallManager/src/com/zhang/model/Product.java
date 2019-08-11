package com.zhang.model;

public class Product {

	private String productId;//商品ID
	private String firstTypeId;//一级分类ID
	private String secondTypeId;//二级分类ID
	private String productName;//商品名称
	private String productDesc;//商品描述
	private double productPrice;//商品价格
	private int    productNums;//商品库存
	private String picture;		//商品图片
	private String productStatus;//商品状态
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Product(String productId, String productName, double productPrice, int productNums, String productStatus) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productNums = productNums;
		this.productStatus = productStatus;
	}



	public Product(String firstTypeId, String secondTypeId, String productName, String productDesc, double productPrice,
			int productNums, String picture) {
		super();
		this.firstTypeId = firstTypeId;
		this.secondTypeId = secondTypeId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productNums = productNums;
		this.picture = picture;
	}



	public Product(String firstTypeId, String secondTypeId, String productName, String productDesc, double productPrice,
			int productNums, String picture, String productStatus) {
		super();
		this.firstTypeId = firstTypeId;
		this.secondTypeId = secondTypeId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productNums = productNums;
		this.picture = picture;
		this.productStatus = productStatus;
	}


	public Product(String productId, String firstTypeId, String secondTypeId, String productName, String productDesc,
			double productPrice, int productNums, String picture, String productStatus) {
		super();
		this.productId = productId;
		this.firstTypeId = firstTypeId;
		this.secondTypeId = secondTypeId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.productPrice = productPrice;
		this.productNums = productNums;
		this.picture = picture;
		this.productStatus = productStatus;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getFirstTypeId() {
		return firstTypeId;
	}
	public void setFirstTypeId(String firstTypeId) {
		this.firstTypeId = firstTypeId;
	}
	public String getSecondTypeId() {
		return secondTypeId;
	}
	public void setSecondTypeId(String secondTypeId) {
		this.secondTypeId = secondTypeId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductNums() {
		return productNums;
	}
	public void setProductNums(int productNums) {
		this.productNums = productNums;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", firstTypeId=" + firstTypeId + ", secondTypeId=" + secondTypeId
				+ ", productName=" + productName + ", productDesc=" + productDesc + ", productPrice=" + productPrice
				+ ", productNums=" + productNums + ", picture=" + picture + ", productStatus=" + productStatus + "]";
	}
	
	

}
