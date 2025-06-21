package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Orderitems {
	@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer orderitemid;
public Integer getOrderitemid() {
	return orderitemid;
}
public void setOrderitemid(Integer orderitemid) {
	this.orderitemid = orderitemid;
}
public Orders getOrders() {
	return orders;
}
public void setOrders(Orders orders) {
	this.orders = orders;
}
public Integer getProductid() {
	return productid;
}
public void setProductid(Integer productid) {
	this.productid = productid;
}
public double getQuantity() {
	return quantity;
}
public void setQuantity(double quantity) {
	this.quantity = quantity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
@ManyToOne
@JsonIgnore
@JoinColumn(name="orders_orderid")
private Orders orders;
public Orderitems() {
	super();
}
public Orderitems(Integer orderitemid, Orders orders, Integer productid, double quantity, double price) {
	super();
	this.orderitemid = orderitemid;
	this.orders = orders;
	this.productid = productid;
	this.quantity = quantity;
	this.price = price;
}
private Integer productid;
private double quantity;
private double price;

}
