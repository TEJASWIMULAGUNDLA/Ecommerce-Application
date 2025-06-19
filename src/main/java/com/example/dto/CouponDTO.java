package com.example.dto;

import java.time.LocalDate;

import com.example.entity.Discounttype;

public class CouponDTO {
	 private Integer couponid;
	   
		public CouponDTO(Integer couponid, String couponcode, String description, double discountvalue,
			Discounttype discountType, double minorderAmount, LocalDate createdtime, LocalDate exiparydate,
			Integer maxUsageCount) {
		super();
		this.couponid = couponid;
		this.couponcode = couponcode;
		this.description = description;
		this.discountvalue = discountvalue;
		this.discountType = discountType;
		this.minorderAmount = minorderAmount;
		createdtime = createdtime;
		this.exiparydate = exiparydate;
		this.maxUsageCount = maxUsageCount;
	}
		public CouponDTO() {
			super();
		}
		public Integer getCouponid() {
		return couponid;
	}
	public void setCouponid(Integer couponid) {
		this.couponid = couponid;
	}
	public String getCouponcode() {
		return couponcode;
	}
	public void setCouponcode(String couponcode) {
		this.couponcode = couponcode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getDiscountvalue() {
		return discountvalue;
	}
	public void setDiscountValue(double discountvalue) {
		this.discountvalue = discountvalue;
	}
	
	public double getMinorderAmount() {
		return minorderAmount;
	}
	public void setMinorderAmount(double minorderAmount) {
		this.minorderAmount = minorderAmount;
	}
	public LocalDate getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(LocalDate createdtime) {
		createdtime = createdtime;
	}
	public LocalDate getExiparydate() {
		return exiparydate;
	}
	public void setExiparydate(LocalDate exiparydate) {
		this.exiparydate = exiparydate;
	}
	public Integer getMaxUsageCount() {          
		return maxUsageCount;
	}
	public void setMaxUsageCount(Integer maxUsageCount) {
		this.maxUsageCount = maxUsageCount;
	}
		private String couponcode;
	    private String description;
	    private double discountvalue;
	    private Discounttype discountType;    // Use String for easy enum serialization
	    public Discounttype getDiscountType() {
			return discountType;
		}
		public void setDiscountType(Discounttype discountType) {
			this.discountType = discountType;
		}
		private double minorderAmount;
	    private LocalDate createdtime;
	    private LocalDate exiparydate;
	    private Integer maxUsageCount;
}
