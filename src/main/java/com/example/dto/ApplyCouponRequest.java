package com.example.dto;

public class ApplyCouponRequest {
private String couponcode;
public ApplyCouponRequest(String couponcode, double totalamount) {
	super();
	this.couponcode = couponcode;
	this.totalamount = totalamount;
}
public String getCouponcode() {
	return couponcode;
}
public void setCouponcode(String couponcode) {
	this.couponcode = couponcode;
}
public double getTotalamount() {
	return totalamount;
}
public void setTotalamount(double totalamount) {
	this.totalamount = totalamount;
}
public ApplyCouponRequest() {
	super();
}
private double totalamount;
}
