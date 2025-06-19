package com.kafka.dto;

public class PaymentStatusEvent {
    private String email;
    private String paymentId;
    private String status; // success or failed
	
    
    public PaymentStatusEvent(String email, String paymentId, String status) {
		super();
		this.email = email;
		this.paymentId = paymentId;
		this.status = status;
	}
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
