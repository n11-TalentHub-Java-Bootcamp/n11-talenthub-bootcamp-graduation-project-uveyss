package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.data.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService {

    public Long calculateCreditScore(UserDto user){
        Random r=new Random(); //random sınıfı
        Long calculateLimit=r.nextLong();
        return calculateLimit;
    }
}
