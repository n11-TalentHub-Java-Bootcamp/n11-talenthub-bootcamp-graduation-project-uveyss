package com.lns.n11loanapplication.engine;

import com.lns.n11loanapplication.data.constants.CreditsConstans;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Consumer {


    @Value("${kafka.topic.calculateCreditScore}")
    private String calculateCreditScore;
    @Value("${kafka.topic.calculateCreditScoreRetry}")
    private String calculateCreditScoreRetry;

    @Value("sendCreditLimitResultByEmail")
    private String sendCreditLimitResultByEmail;
    private final KafkaTemplate kafkaTemplate;


    public Consumer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCalculateCreditScoreEvent(String userTckn) {
         String messageId=UUID.randomUUID().toString();
        try {
            kafkaTemplate.send(calculateCreditScore, messageId, userTckn);
        }
        catch (Exception listenerException)
        {
         kafkaTemplate.send(calculateCreditScoreRetry,messageId);
        }

    }






}
