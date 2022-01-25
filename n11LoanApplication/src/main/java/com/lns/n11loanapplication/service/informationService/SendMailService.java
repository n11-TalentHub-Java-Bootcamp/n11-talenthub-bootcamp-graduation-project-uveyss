package com.lns.n11loanapplication.service.informationService;


import com.lns.n11loanapplication.engine.notificationEngine.MailEngine;
import org.springframework.stereotype.Component;

@Component
public class SendMailService implements ISendInformation {


    private final MailEngine emailSender;

    public SendMailService(MailEngine emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendInformation(String to, String message) {
      emailSender.sendEmail(to,"",message);
    }
}

