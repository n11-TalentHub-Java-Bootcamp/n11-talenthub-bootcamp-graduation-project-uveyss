package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.converter.UserConverter;
import com.lns.n11loanapplication.converter.UserCreditConverter;
import com.lns.n11loanapplication.dao.CreditDao;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class CreditService {
    @Autowired
    CreditScoreService creditScoreService;

    @Autowired
    CreditDao creditDao;

    @Autowired
    CreditDetailService creditDetailService;
    @Autowired
    UserService userService;
    public UserCreditDto save (UserCreditDto userCreditDto)
    {
        Credit credit= UserCreditConverter.INSTANCE.userCreditDtoConvertToCredit(userCreditDto);
        credit=creditDao.save(credit);
        return  creditDetailService.save(userCreditDto);
    }

    public UserCreditDto calculateCreditLimit(String userTckn){
        UserDto user =userService.findByUserTckn(Long.valueOf(userTckn));
        Long creditScore = creditScoreService.calculateCreditScore(user);
        //TODO :user
        UserCreditDto userCreditDto= UserConverter.INSTANCE.userDtoConvertToUserCreditDto(user);
        userCreditDto.setCreditScore(creditScore);
        userCreditDto.setCreditAmount(BigDecimal.valueOf(creditScore));
        userCreditDto.setCreditStatus(Byte.valueOf("2"));
        userCreditDto.setRequestDate(new Date(System.currentTimeMillis()));

        userCreditDto=save(userCreditDto);

        return userCreditDto;
    }
}
