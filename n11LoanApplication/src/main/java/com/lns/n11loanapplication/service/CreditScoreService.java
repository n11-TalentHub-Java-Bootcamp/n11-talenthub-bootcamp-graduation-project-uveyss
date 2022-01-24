package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.data.dto.UserCreditDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService {

    public Long calculateCreditScore(UserCreditDto user){
        Random r=new Random();
        Long calculateLimit=Long.valueOf(650);
        return calculateLimit;
    }
}
