package com.example.dto;

public class OrderItemDTO {
	private Integer orderitemid;
    private Integer productid;
    private double quantity;
    private double price;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Integer orderitemid, Integer productid, double quantity, double price,Integer cartid) {
        this.orderitemid = orderitemid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
        this.cartid=cartid;
    }

    // Getters and Setters
    public Integer getOrderitemid() {
        return orderitemid;
    }

    public void setOrderitemid(Integer orderitemid) {
        this.orderitemid = orderitemid;
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
    private Integer cartid;

	public Integer getCartid() {
		return cartid;
	}

	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}

}
