package com.lns.n11loanapplication.engine;

import com.lns.n11loanapplication.data.constants.CreditsConstans;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.service.CreditService;
import com.lns.n11loanapplication.service.UserService;
import com.lns.n11loanapplication.service.informationService.SendMailService;
import com.lns.n11loanapplication.service.informationService.SendSmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Listener {
    @Autowired
    CreditService creditService;
    @Autowired
    SendMailService sendMailService;
    @Autowired
    SendSmsService sendSmsService;

    @Autowired
    UserService userService;


    private  UserCreditDto userCreditDto;
    private UserDto userDto;

    public Listener(CreditService creditService) {
        this.creditService = creditService;
    }



    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "false",
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE)
    @KafkaListener(topics = "${kafka.topic.calculateCreditScore}", groupId = "${kafka.groupId}")
    public void calculateCreditScoreListener(@Payload String userTckn) {
        try {
                userCreditDto=creditService.prepareUserCreditDtoForCreditApproval(Long.valueOf(userTckn));
                 userDto = userService.findByUserTckn(Long.valueOf(userTckn));


                 sendSmsService.sendInformation("+90"+userDto.getUserPhone().toString(), CreditsConstans.getCreditLimitResultMessage() + userCreditDto.getCreditAmount().toString());
            }
            catch (Exception ex)
            {
                dlt(ex.getLocalizedMessage(), "kafka.topic.calculateCreditScore");
            }
    }

    @DltHandler
    public void dlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.warn(in + " from " + topic);/*Yeni bir topic açılarak log gb olarak elastic search e basılabilir.*/
       // sendMailService.sendInformation(CreditsConstans.getKafkaAdminMail(), CreditsConstans.getSmsDidNotSend() + getUserCreditDto().getUserPhone());
    }

}