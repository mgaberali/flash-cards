package com.mg.flashcards.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.mg.flashcards.services.EmailService;

@Component
public class EmailServiceImpl implements EmailService{

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void send(final String to, final String subjsct, final String text) throws Exception{
        final SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setTo(to);
            message.setSubject(subjsct);
            message.setText(text);
            emailSender.send(message);
        } catch (final Exception e) {
            throw new Exception("internal server error, error while sending confirmation email");
        }

    }
}
