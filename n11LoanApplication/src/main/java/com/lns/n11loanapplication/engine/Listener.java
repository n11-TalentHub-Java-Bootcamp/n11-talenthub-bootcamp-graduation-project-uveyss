package com.lns.n11loanapplication.engine;

import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Listener {
    @Autowired
    private CreditService creditService;

    public Listener(CreditService creditService) {
        this.creditService = creditService;
    }

    @KafkaListener(topics = "${kafka.topic.calculateCreditScore}", groupId = "${kafka.groupId}")
    public void calculateCreditScoreListener(@Payload String userTckn) {

        UserCreditDto userCreditDto = creditService.calculateCreditLimit(userTckn);


    }
}