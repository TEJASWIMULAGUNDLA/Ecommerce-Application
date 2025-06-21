package com.example.dto;

import java.util.List;

public class CartDto {
private Integer cartid;
public CartDto(Integer cartid, Integer userid) {
	super();
	this.cartid = cartid;
	this.userid = userid;
}
public CartDto() {
	super();
}
public Integer getCartid() {
	return cartid;
}
public void setCartid(Integer cartid) {
	this.cartid = cartid;
}
public Integer getUserid() {
	return userid;
}
public void setUserid(Integer userid) {
	this.userid = userid;
}
private Integer userid;

}
