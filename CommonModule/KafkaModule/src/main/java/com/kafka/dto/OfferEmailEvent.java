package com.kafka.dto;

public class OfferEmailEvent {
    private String userEmail;
    private String subject;
    private String offerMessage;
    private String unsubscribeLink;
    
	public OfferEmailEvent(String userEmail, String subject, String offerMessage,String unsubscribeLink) {
		super();
		this.userEmail = userEmail;
		this.subject = subject;
		this.offerMessage = offerMessage;
		this.unsubscribeLink=unsubscribeLink;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOfferMessage() {
		return offerMessage;
	}
	public void setOfferMessage(String offerMessage) {
		this.offerMessage = offerMessage;
	}
	public String getUnsubscribeLink() {
		return unsubscribeLink;
	}
	public void setUnsubscribeLink(String unsubscribeLink) {
		this.unsubscribeLink = unsubscribeLink;
	}
	
    
    
    
}
