package com.example.demo.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.service.EmailService;
import com.kafka.KafkaConstant;
import com.kafka.dto.AccountVerifiedEvent;
import com.kafka.dto.OfferEmailEvent;
import com.kafka.dto.OrderStatusEvent;
import com.kafka.dto.OtpVerificationEvent;
import com.kafka.dto.PaymentStatusEvent;

@Component
public class NotificationListner {
	
	@Autowired
	private EmailService service;
	
	@KafkaListener(topics = KafkaConstant.Topic_EMAIL_OTP,groupId ="notification-group")
	public void otpVerification(OtpVerificationEvent otp) {
		service.sendOtpEmail(otp.getEmail(), otp.getOtp());
	}
	
	@KafkaListener(topics = KafkaConstant.Topic_EMAIL_VERIFIED,groupId ="notification-group")
	public void accountVerified(AccountVerifiedEvent account) {
		service.sendAccounrtVerifiedMail(account.getEmail(), account.getUsername());
	}
	
	@KafkaListener(topics = KafkaConstant.Topic_ORDER_STATUS,groupId ="notification-group")
	public void orderStatus(OrderStatusEvent order) {
		service.sendOrderStatusEmail(order.getEmail(), order.getOrderId(), order.getStatus());
	}
	@KafkaListener(topics =KafkaConstant.Topic_PAYMENT_STATUS,groupId = "notification-group")
	public void paymentStatusEvent(PaymentStatusEvent payment) {
		service.sendPaymentStatusMail(payment.getEmail(), payment.getPaymentId(), payment.getStatus());
	}
	
	@KafkaListener(topics = KafkaConstant.Topic_PROMOTION_EMAIL, groupId = "notification-group")
	public void sendPromotionalEmail(OfferEmailEvent event) {
	    String body = event.getOfferMessage();
	    
	    // Include Unsubscribe link if applicable
	    if (event.getUnsubscribeLink() != null) {
	        body += "\n\nTo unsubscribe, click here: " + event.getUnsubscribeLink();
	    }

	    service.sendEmail(event.getUserEmail(), event.getSubject(), body);
	}

	

}