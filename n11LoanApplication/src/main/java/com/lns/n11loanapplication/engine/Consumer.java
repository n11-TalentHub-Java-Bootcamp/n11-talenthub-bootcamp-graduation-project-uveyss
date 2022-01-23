package com.lns.n11loanapplication.engine;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Consumer {


    @Value("${kafka.topic.calculateCreditScore}")
    private String calculateCreditScore;

    private final KafkaTemplate kafkaTemplate;


    public Consumer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishCalculateCreditScoreEvent(String userTckn) {
        kafkaTemplate.send(calculateCreditScore, UUID.randomUUID().toString(), userTckn);
    }

}
