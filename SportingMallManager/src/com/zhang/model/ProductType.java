package com.zhang.model;

public class ProductType {
	
	private String typeId;
	private String typeName;
	private String picture;
	
	
	public ProductType() {
		super();
		// TODO Auto-generated constructor stub
	}



	public ProductType(String typeName) {
		super();
		this.typeName = typeName;
	}



	public ProductType(String typeId, String typeName,String picture) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.picture=picture;
	}



	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String getTypeId() {
		return typeId;
	}



	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}



	public String getTypeName() {
		return typeName;
	}



	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}



	@Override
	public String toString() {
		return "ProductType [typeId=" + typeId + ", typeName=" + typeName + ", picture=" + picture + "]";
	}




	
}