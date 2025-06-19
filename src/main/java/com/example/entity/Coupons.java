package com.example.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Coupons {
          @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer couponid;
	 public Coupons(Integer couponid, String couponcode, String decsription, double discountvalue,
				Discounttype discountType, double minorderAmount, LocalDate createdtime, LocalDate exiparydate,
				Integer maxUsageCount) {
			super();
			this.couponid = couponid;
			this.couponcode = couponcode;
			this.decsription = decsription;
			this.discountvalue = discountvalue;
			this.discountType = discountType;
			this.minorderAmount = minorderAmount;
			this.createdtime =createdtime;
			this.exiparydate = exiparydate;
			this.maxUsageCount = maxUsageCount;
		}
	 public Coupons() {
	 
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
		public String getDecsription() {
			return decsription;
		}
		public void setDecsription(String decsription) {
			this.decsription = decsription;
		}
		public double getDiscountvalue() {
			return discountvalue;
		}
		public void setDiscountvalue(double discountvalue) {
			this.discountvalue = discountvalue;
		}
		public Discounttype getDiscountType() {
			return discountType;
		}
		public void setDiscountType(Discounttype discountType) {
			this.discountType = discountType;
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
	@Column(unique = true, nullable = false)
	 private String couponcode;
	
	 private String decsription;
	 private double discountvalue;
	    @Enumerated(EnumType.STRING)
	 private Discounttype discountType;
	 private double minorderAmount;
	 private LocalDate createdtime;
	 private LocalDate exiparydate;
	 private Integer maxUsageCount; 
	 

	 
}
