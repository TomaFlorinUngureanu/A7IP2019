package com.billManagement.sendBill;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class JavaMailSender {
	 @Bean
	    public JavaMailSenderImpl getJavaMailSender() {
	        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	        mailSender.setHost("smtp.gmail.com");
	        mailSender.setPort(587);
	         
	        mailSender.setUsername("12345zornet123452@gmail.com");
	        mailSender.setPassword("2u3pu2u3pu");
	         
	        Properties props = mailSender.getJavaMailProperties();
	        props.put("mail.transport.protocol", "smtp");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.debug", "true");
	         
	        return mailSender;
	    }
}
