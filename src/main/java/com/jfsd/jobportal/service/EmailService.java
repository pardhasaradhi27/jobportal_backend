package com.jfsd.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendStatusEmail(String toEmail, String firstName, String jobName, String status) {
        SimpleMailMessage message = new SimpleMailMessage();

        // Explicitly set the sender's email address
        message.setFrom("your-email@gmail.com"); // Replace with your email address

        message.setTo(toEmail);
        message.setSubject("Application " + status);
        message.setText("Dear " + firstName + ",\n\nYour application for the position of "
                + jobName + " has been " + status + ".\n\nBest regards,\nJob Portal Team");

        mailSender.send(message);
    }
}
