package com.lns.n11loanapplication.engine;

import com.lns.n11loanapplication.data.constants.CreditsConstans;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.service.CreditService;
import com.lns.n11loanapplication.service.informationService.SendMailService;
import com.lns.n11loanapplication.service.informationService.SendSmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @Autowired
    CreditService creditService;
    @Autowired
    SendMailService sendMailService;
    @Autowired
    SendSmsService sendSmsService;

    public Listener(CreditService creditService) {
        this.creditService = creditService;
    }


    @KafkaListener(topics = "${kafka.topic.calculateCreditScore}", groupId = "${kafka.groupId}")
    public void calculateCreditScoreListener(@Payload String userTckn) {
        UserCreditDto userCreditDto=   creditService.calculateCreditLimit(userTckn);
        sendMailService.sendInformation("doganvey@outlook.com", CreditsConstans.getCreditLimitResultMessage() +userCreditDto.getCreditAmount().toString());
        sendSmsService.sendInformation("+905357479474",CreditsConstans.getCreditLimitResultMessage() +userCreditDto.getCreditAmount().toString());
    }
}