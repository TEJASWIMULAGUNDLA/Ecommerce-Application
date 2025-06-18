package com.example.demo.consumer;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.service.EmailService;
import com.kafka.dtos.AccountVerifiedEvent;
import com.kafka.dtos.OfferEmailEvent;
import com.kafka.dtos.OrderStatusEvent;
import com.kafka.dtos.OtpVerificationEvent;
import com.kafka.dtos.PaymentStatusEvent;


@ExtendWith(MockitoExtension.class)
public class NotificationListnerTest {
	
	@Mock
	private EmailService service;
	
	@InjectMocks
	private NotificationListner listner;
	
	@Test
	public void otpVerificationTest() {
		OtpVerificationEvent event = new OtpVerificationEvent("test@abc","12345");
		listner.otpVerification(event);
		verify(service).sendOtpEmail("test@abc","12345");
	}
	
    @Test
	public void accountVerifiedTest() {
		AccountVerifiedEvent event = new AccountVerifiedEvent("test#abc", "Verified");
		listner.accountVerified(event);
		verify(service).sendAccounrtVerifiedMail("test#abc", "Verified");
	}
	
	@Test
	public void orderStatusTest() {
		OrderStatusEvent event = new OrderStatusEvent("test@abc","1234", "Placed");
		listner.orderStatus(event);
		verify(service).sendOrderStatusEmail("test@abc","1234","Placed");
	}
	
	@Test
	public void paymentStatusEventTest() {
		PaymentStatusEvent event = new PaymentStatusEvent("test@abc", "12345", "Success");
		listner.paymentStatusEvent(event);
		verify(service).sendPaymentStatusMail("test@abc", "12345", "Success");
	}
	
	@Test
	public void sendPromotionalEmailTest() {
		String to ="test@abc";
		String subject="🎁 Today's Special Offer Just for You!";
		String body="Get 25% off on your next purchase! Hurry, offer ends today!";
		OfferEmailEvent offerEmailEvent = new OfferEmailEvent(to,subject, body, null);
		listner.sendPromotionalEmail(offerEmailEvent);
		
		verify(service).sendEmail(to, subject, body);
		
	}
	@Test
	public void sendPromotionalEmailTest2() {
		String to ="test@abc";
		String subject="🎁 Today's Special Offer Just for You!";
		String body="Get 25% off on your next purchase! Hurry, offer ends today!";
		OfferEmailEvent offerEmailEvent = new OfferEmailEvent(to,subject, body, "link");
		listner.sendPromotionalEmail(offerEmailEvent);
		
		verify(service).sendEmail(to, subject, body += "\n\nTo unsubscribe, click here: " + "link");
		
	}
	
	
}
