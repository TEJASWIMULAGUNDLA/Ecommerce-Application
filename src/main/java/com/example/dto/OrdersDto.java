package com.example.dto;

import java.time.LocalDate;
import java.util.List;

import com.example.entity.Coupons;
import com.example.entity.Orderstatus;

public class OrdersDto {
	 private Integer orderid;
	    private Integer userid;
	    private String userPermanentAddress;
	    private String deliveryAddress;
	    private double totalAmount;
	    private double gst;
	    private LocalDate createdAt;
	    // Use String to map enum easily in JSON
	    private List<OrderItemDTO> orderItems;
	    private Integer cartid;
	    private Orderstatus orderstatus;

	    // Constructors
	   

	    public Orderstatus getOrderstatus() {
			return orderstatus;
		}

		public void setOrderstatus(Orderstatus orderstatus) {
			this.orderstatus = orderstatus;
		}

		public Integer getCartid() {
			return cartid;
		}

		public void setCartid(Integer cartid) {
			this.cartid = cartid;
		}

		// Getters and Setters
	    public Integer getOrderid() {
	        return orderid;
	    }

	    public OrdersDto(Integer orderid, Integer userid, String userPermanentAddress, String deliveryAddress,
				double totalAmount, double gst, LocalDate createdAt, Orderstatus orderstatus,
				List<OrderItemDTO> orderItems,Integer cartid,Coupons coupon) {
			super();
			this.orderid = orderid;
			this.userid = userid;
			this.userPermanentAddress = userPermanentAddress;
			this.deliveryAddress = deliveryAddress;
			this.totalAmount = totalAmount;
			this.gst = gst;
			this.createdAt = createdAt;
			this.orderstatus=orderstatus;
			this.orderItems = orderItems;
			this.cartid=cartid;
			this.coupon=coupon;
		}

		public OrdersDto() {
			super();
		}

		public void setOrderid(Integer orderId) {
	        this.orderid = orderid;
	    }

	    public Integer getUserid() {
	        return userid;
	    }

	    public void setUserId(Integer userid) {
	        this.userid = userid;
	    }

	    public String getUserPermanentAddress() {
	        return userPermanentAddress;
	    }

	    public void setUserPermanentAddress(String userPermanentAddress) {
	        this.userPermanentAddress = userPermanentAddress;
	    }

	    public String getDeliveryAddress() {
	        return deliveryAddress;
	    }

	    public void setDeliveryAddress(String deliveryAddress) {
	        this.deliveryAddress = deliveryAddress;
	    }

	    public double getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(double totalAmount) {
	        this.totalAmount = totalAmount;
	    }

	    public double getGst() {
	        return gst;
	    }

	    public void setGst(double gst) {
	        this.gst = gst;
	    }

	    public LocalDate getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDate createdAt) {
	        this.createdAt = createdAt;
	    }

	  

	    public List<OrderItemDTO> getOrderItems() {
	        return orderItems;
	    }

	    public void setOrderItems(List<OrderItemDTO> orderItems) {
	        this.orderItems = orderItems;
	    }
	    private Coupons coupon;
	    public Coupons getCoupon() {
	    	return coupon;
	    }
	    public void setCoupon(Coupons coupon) {
	    	this.coupon = coupon;
	    }
}
