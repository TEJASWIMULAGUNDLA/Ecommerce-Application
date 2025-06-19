package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.demo.feign.client.UserFeignClient;
import com.kafka.KafkaConstant;
import com.kafka.dto.OfferEmailEvent;
import com.kafka.dto.User;


@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
	
	@Mock
	private JavaMailSender sender;

	@Mock
	private KafkaTemplate<String, Object> kafkaTemplate;
	@Mock
	private UserFeignClient userClient;
	
	@InjectMocks
	private EmailService service;
	@Captor
	private ArgumentCaptor<SimpleMailMessage> messageCaptor;

	@Test
	public void sendEmailTest() {
		String to = "test@abc";
		String subject = "good";
		String body = "body";
		
		service.sendEmail(to, subject, body);
		

		verify(sender, times(1)).send(messageCaptor.capture());

		SimpleMailMessage sentMessage = messageCaptor.getValue();

		assert sentMessage.getTo()[0].equals(to);
		assert sentMessage.getSubject().equals(subject);
		assert sentMessage.getText().equals(body);

	}
	@Test
	public void sendOtpEmailTest() {
		String to = "test@abc";
		String subject ="Otp Code";
		String otp="1234";
		String body = "Your verification code : "+otp;
		
		service.sendOtpEmail(to, otp);
		
		verify(sender,times(1)).send(messageCaptor.capture());
		SimpleMailMessage sentMessage = messageCaptor.getValue();
		
		assert sentMessage.getTo()[0].equals(to);
		assert sentMessage.getSubject().equals(subject);
		assert sentMessage.getText().equals(body);
	}

	@Test
	public void sendAccounrtVerifiedMailTest() {
		String to = "test@abc";
		String subject = "Account verified";
		String username="sai";
		String body = "Hi "+username +", your account has been successfully verified.";
		
		service.sendAccounrtVerifiedMail(to, username);
		verify(sender,times(1)).send(messageCaptor.capture());
		SimpleMailMessage sentMessage = messageCaptor.getValue();
		assert sentMessage.getTo()[0].equals(to);
		assert sentMessage.getSubject().equals(subject);
		assert sentMessage.getText().equals(body);
	}
	@Test
	public void sendOrderStatusEmailTest() {
		String to = "test@abc";
		String status="success";
		String subject = "Order " + status.toUpperCase();
		
		String orderId="12345";
		String body = "Your order ID " + orderId + " has been " + status + "." ;
		
		service.sendOrderStatusEmail(to, orderId, status);
		verify(sender,times(1)).send(messageCaptor.capture());
		
		SimpleMailMessage sentMessage = messageCaptor.getValue();
		
		assert sentMessage.getTo()[0].equals(to);
		assert sentMessage.getSubject().equals(subject);
		assert sentMessage.getText().equals(body);
		
	}
	
	@Test
	public void sendPaymentStatusMailTest() {
		String to = "test@abc";
		String status="success";
		String subject ="Payment " + status.toUpperCase();
		
		String paymentId="12345";
		String body ="Your payment ID " + paymentId + " was " + status + ".";
		service.sendPaymentStatusMail(to, paymentId, status);
		verify(sender,times(1)).send(messageCaptor.capture());
		
		SimpleMailMessage sentMessage = messageCaptor.getValue();
		
		assertEquals(to, sentMessage.getTo()[0]);
		assertEquals(subject, sentMessage.getSubject());
		assertEquals(body, sentMessage.getText());
	}
	
	@Test
	public void sendPromotionMailsTest() {
		
	    User user = new User("test@abc");	    
	    Set<User> users = new HashSet<>();
	    users.add(user);
	    when(userClient.subscribedUsers()).thenReturn(users);
	    service.sendPromotionMails();

	    verify(kafkaTemplate, times(1)).send(eq(KafkaConstant.Topic_PROMOTION_EMAIL), any(OfferEmailEvent.class));
	}

}