package com.kafka.dto;

public class OrderStatusEvent {
	
    private String email;
    private String orderId;
    // success or failed
    private String status; 
    
    
    
	public OrderStatusEvent(String email, String orderId, String status) {
		super();
		this.email = email;
		this.orderId = orderId;
		this.status = status;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    

}
