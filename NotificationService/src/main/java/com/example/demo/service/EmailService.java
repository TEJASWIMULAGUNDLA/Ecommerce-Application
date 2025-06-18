package com.example.demo.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.client.UserFeignClient;
import com.kafka.KafkaConstant;
import com.kafka.dtos.OfferEmailEvent;
import com.kafka.dtos.User;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	@Autowired
	private UserFeignClient userClient;
	
	public void sendEmail(String to ,String subject, String body ) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		sender.send(message);
		
	}
	//sending otp by mail
	public void sendOtpEmail(String to , String otp) {
		sendEmail(to,"Otp Code","Your verification code : "+otp);
	}
	//sending account verification mail
	public void sendAccounrtVerifiedMail(String to , String username) {
		sendEmail( to,
				"Account verified",
				"Hi "+username +", your account has been successfully verified.");
	}
	// sending order status 
	public void sendOrderStatusEmail(String to,String orderId,String status) {
        sendEmail(to, "Order " + status.toUpperCase(), "Your order ID " + orderId + " has been " + status + ".");
	}
	//sending payment status 
	public void sendPaymentStatusMail(String to, String paymentId, String status) {
        sendEmail(to, "Payment " + status.toUpperCase(), "Your payment ID " + paymentId + " was " + status + ".");
    }
	
	@Scheduled(cron = "0 0 9 * * *")
	public void sendPromotionMails() {
	    Set<User> users = userClient.subscribedUsers(); 
	    String baseUnsubscribeUrl = "https:/user/unsubscribe";

	    users.forEach(user -> {
	        String unsubscribeLink = baseUnsubscribeUrl + user.getEmail();

	        OfferEmailEvent event = new OfferEmailEvent(
	            user.getEmail(),
	            "🎁 Today's Special Offer Just for You!",
	            "Get 25% off on your next purchase! Hurry, offer ends today!",
	            unsubscribeLink
	        );

	        kafkaTemplate.send(KafkaConstant.Topic_PROMOTION_EMAIL, event);
	    });
	}

}
