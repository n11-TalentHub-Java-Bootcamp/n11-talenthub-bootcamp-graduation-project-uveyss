package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.converter.CreditConverter;
import com.lns.n11loanapplication.converter.UserCreditConverter;
import com.lns.n11loanapplication.converter.UserCreditDetailConverter;
import com.lns.n11loanapplication.dao.CreditDao;
import com.lns.n11loanapplication.data.constants.CreditApprovalStatus;
import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.dto.CreditDto;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.service.CreditLimit.*;
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



    public CreditDto save (CreditDto creditDto)
    {
        Credit credit= CreditConverter.INSTANCE.creditDtoConvertToCredit(creditDto);
        credit=creditDao.save(credit);
        creditDto=CreditConverter.INSTANCE.creditConvertToCreditDto(credit);
        return creditDto;
    }

    public UserCreditDto prepareUserCreditDtoForCreditApproval(String userTckn)
    {
        UserCreditDto userCreditDto =userService.findUserForCreditByTckn(Long.valueOf(userTckn));
        Long creditScore = creditScoreService.calculateCreditScore(userCreditDto);
        userCreditDto.setCreditScore(creditScore);
        userCreditDto.setRequestDate(new Date(System.currentTimeMillis()));
        return userCreditDto;
    }


    public UserCreditDto calculateCreditLimit(String userTckn){
        UserCreditDto userCreditDto =prepareUserCreditDtoForCreditApproval(userTckn);
        BigDecimal assignedLimit =BigDecimal.ZERO;
        if(userCreditDto.getCreditScore()<500)
        {
            userCreditDto.setCreditStatus(CreditApprovalStatus.REJECTED.getApprovalStatus());
        }
        if(userCreditDto.getCreditScore()>500 && userCreditDto.getCreditScore()<1000)
        {
            CreditLimitAssignService creditLimitAssignService = CreditLimitAssignService.getInstance();
            if(userCreditDto.getMontlyIncome().compareTo(BigDecimal.valueOf(5000))<0)//TODO Maaş Oranları parametrik yapılacak.
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new LowCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            else if(userCreditDto.getMontlyIncome().compareTo(BigDecimal.valueOf(10000))<0 )
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new MidCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            else
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new HighCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            userCreditDto.setCreditStatus(CreditApprovalStatus.APPROVED.getApprovalStatus());
        }
        else
        {
            userCreditDto.setCreditStatus(CreditApprovalStatus.REJECTED.getApprovalStatus());
        }
        userCreditDto.setCreditAmount(assignedLimit);
        saveCreditAndCreditDetail(userCreditDto);
        return userCreditDto;
    }


    public void saveCreditAndCreditDetail(UserCreditDto userCreditDto)
    {
        CreditDto creditDto= UserCreditConverter.INSTANCE.userCreditDtoConvertToCreditDto (userCreditDto);
        creditDto=save(creditDto);
        CreditDetailDto creditDetailDto= UserCreditDetailConverter.INSTANCE.userCreditDtoConvertToCreditDetailDto(userCreditDto);
        creditDetailDto.setCredit(creditDto);
        creditDetailService.save(creditDetailDto);
    }

    public void deleteAll()
    {
        userService.deleteAll();
        creditDao.deleteAll();
        creditDetailService.creditDetailDao.deleteAll();
    }
}
