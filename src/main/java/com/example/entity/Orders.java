package com.example.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Table(name="orders")
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer orderid;
public Orders(Integer orderid, Integer userid, String userpermanentadrress, String deliveryaddress,
			double totalamount, LocalDate createdat, Orderstatus orderstatus,double gst,List<Orderitems> orderItems,Integer cartiid,Coupons coupon) {
		super();
		this.orderid = orderid;
		this.userid = userid;
		this.userpermanentadrress = userpermanentadrress;
		this.deliveryaddress = deliveryaddress;
		this.totalamount = totalamount;
		this.createdat = createdat;
		this.orderstatus = orderstatus;
		this.gst=gst;
		this.orderItems=orderItems;
		this.cartid=cartid;
		this.coupon=coupon;
	}
public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUserpermanentadrress() {
		return userpermanentadrress;
	}
	public void setUserpermanentadrress(String userpermanentadrress) {
		this.userpermanentadrress = userpermanentadrress;
	}
	public String getDeliveryaddress() {
		return deliveryaddress;
	}
	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}
	public double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(double totalamount) {
		this.totalamount = totalamount;
	}
	public LocalDate getCreatedat() {
		return createdat;
	}
	public void setCreatedat(LocalDate createdat) {
		this.createdat = createdat;
	}
	public Orderstatus getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}
private Integer userid;
public Orders() {
	super();
}
private String userpermanentadrress;
private String deliveryaddress;
private double totalamount;
private LocalDate createdat;
@Enumerated(EnumType.STRING) 
@Column(nullable = true)
private Orderstatus orderstatus;
private Integer cartid;

public Integer getCartid() {
	return cartid;
}
public void setCartid(Integer cartid) {
	this.cartid = cartid;
}
@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Orderitems> orderItems = new ArrayList<>();
public List<Orderitems> getOrderItems() {
    return orderItems;
}

public void setOrderItems(List<Orderitems> orderItems) {
    this.orderItems = orderItems;
}
private double gst;
public double getGst() {
	return gst;
}
public void setGst(double gst) {
	this.gst = gst;
}
@ManyToOne
@JoinColumn(name = "coupon_couponid") // foreign key column in orders table
private Coupons coupon;
public Coupons getCoupon() {
	return coupon;
}
public void setCoupon(Coupons coupon) {
	this.coupon = coupon;
}


}
