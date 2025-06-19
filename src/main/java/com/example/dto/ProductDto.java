package com.example.dto;

public class ProductDto {
private Integer productid;
public ProductDto(Integer productid, Integer name, String description, double price, double stock,double GstRate,String category) {
	super();
	this.productid = productid;
	this.name = name;
	this.description = description;
	this.price = price;
	this.stock = stock;
	this.GstRate=GstRate;
	this.category=category;
}
public ProductDto() {
	super();
}
public Integer getProductid() {
	return productid;
}
public void setProductid(Integer productid) {
	this.productid = productid;
}
public Integer getName() {
	return name;
}
public void setName(Integer name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public double getStock() {
	return stock;
}
public void setStock(double stock) {
	this.stock = stock;
}
private Integer name;
private String description;
private double price;
private double stock;
private double GstRate;
public double getGstRate() {
	return GstRate;
}
public void setGstRate(double gstRate) {
	GstRate = gstRate;
}
private String category;
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

}
