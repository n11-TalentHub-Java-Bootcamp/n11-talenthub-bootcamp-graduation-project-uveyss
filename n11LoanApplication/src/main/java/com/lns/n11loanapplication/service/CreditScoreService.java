package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.data.dto.UserCreditDto;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CreditScoreService {

    public int calculateCreditScore(UserCreditDto user){

        int minimum = 480;
        int maksimum = 1100;

        int randomSayi = ThreadLocalRandom.current().nextInt(minimum, maksimum + 1);
        return randomSayi;
    }
}
