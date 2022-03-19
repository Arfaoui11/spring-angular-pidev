package com.javachinna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService implements ISendEmailService {
    @Autowired
    private JavaMailSender mailSender;
    public void sendSimpleEmail( String toEmail,
                                String body,
                                String subject) {
        System.out.println("sending Email");
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("hajjej.farouk6@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail Sent successfully......");
    }
}
