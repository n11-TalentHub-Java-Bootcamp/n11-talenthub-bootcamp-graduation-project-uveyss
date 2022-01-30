package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.api.errorHandling.exception.NotFoundException;
import com.lns.n11loanapplication.converter.CreditConverter;
import com.lns.n11loanapplication.converter.UserCreditConverter;
import com.lns.n11loanapplication.converter.UserCreditDetailConverter;
import com.lns.n11loanapplication.data.constants.CreditApprovalStatus;
import com.lns.n11loanapplication.data.constants.CreditsConstans;
import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.dto.CreditDto;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.engine.Consumer;
import com.lns.n11loanapplication.service.creditLimit.*;
import com.lns.n11loanapplication.service.entityService.CreditEntityService;
import com.lns.n11loanapplication.util.DateConverterUtil;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class CreditService {
    @Autowired
    CreditScoreService creditScoreService;

    private final Consumer consumer;

    public CreditService(Consumer consumer) {
        this.consumer = consumer;
    }

    @Autowired
    CreditDetailService creditDetailService;
    @Autowired
    UserService userService;

    @Autowired
    CreditEntityService creditEntityService;


    public CreditDto save (CreditDto creditDto)
    {

        try
        {
                Credit credit= CreditConverter.INSTANCE.creditDtoConvertToCredit(creditDto);
                credit=creditEntityService.save(credit);
                creditDto=CreditConverter.INSTANCE.creditConvertToCreditDto(credit);
                return creditDto;
        }
        catch (Exception ex)
        {
            log.error("save or update exception in CreditService",ex);
            throw new BusinessException("CreditService's save methods has an exception ",ex.getLocalizedMessage());

        }
    }



    public UserCreditDto prepareUserCreditDtoForCreditsApproval(String userTckn)
    {
        UserCreditDto userCreditDto =userService.findUserForCreditByTckn(Long.valueOf(userTckn));
        int creditScore = creditScoreService.calculateCreditScore(userCreditDto);
        userCreditDto.setCreditScore(creditScore);
        java.util.Date requestDate=new java.util.Date();
        userCreditDto.setRequestDate(requestDate);
        return userCreditDto;
    }



    public UserCreditDto prepareUserCreditDtoForCreditApproval(Long tckn, String birthDate)
    {
        DateConverterUtil dateConverter = new DateConverterUtil();
        LocalDate convertedDate =dateConverter.parseDate(birthDate);
        Credit credit =findCreditApprovalByTcknAndBirthDate(tckn,convertedDate);
        return getCreditInfo( credit);
    }


    public UserCreditDto prepareUserCreditDtoForCreditApproval(Long tckn)
    {
        Credit credit =creditEntityService.findByTckn(tckn);
        return getCreditInfo( credit);
    }

    public UserCreditDto getCreditInfo(Credit credit)
    {
        CreditDto creditDto =CreditConverter.INSTANCE.creditConvertToCreditDto(credit);
        CreditDetailDto creditDetailDto = creditDetailService.findByCreditId(creditDto.getCreditId());
        UserCreditDto userCreditDto =UserCreditConverter.INSTANCE.creditDtoConvertToUserCreditDto(creditDto);
        userCreditDto.setCreditAmount(creditDetailDto.getCreditAmount());
        userCreditDto.setColleteralAmount(creditDetailDto.getColleteralAmount());
        userCreditDto.setCreditApprovalDate(creditDetailDto.getCreditApprovalDate());
        userCreditDto.setCreditScore(creditDetailDto.getCreditScore());
        return userCreditDto;
    }


    public UserCreditDto calculateCreditLimit(UserDto userDto){
        UserCreditDto userCreditDto =prepareUserCreditDtoForCreditsApproval(userDto.getUserTckn().toString());
        userCreditDto.setMontlyIncome(userCreditDto.getMontlyIncome());
        userCreditDto.setColleteralAmount(userDto.getColleteralAmount());
        userCreditDto.setUserPhone(userDto.getUserPhone());
        BigDecimal assignedLimit =BigDecimal.ZERO;
        CreditLimitAssignService creditLimitAssignService = CreditLimitAssignService.getInstance();
        if(userCreditDto.getCreditScore()<500)
        {
            userCreditDto.setCreditStatus(CreditApprovalStatus.REJECTED.getApprovalStatus());
            assignedLimit=creditLimitAssignService.assignCreditLimit(new CreditLimit(userCreditDto.getMontlyIncome()));
        }
        else if(userCreditDto.getCreditScore()<1000)
        {

            if(userCreditDto.getMontlyIncome().compareTo(CreditsConstans.getLowMonthlyIncome())<0)
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new LowCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            else if(userCreditDto.getMontlyIncome().compareTo(CreditsConstans.getMidMonthlyIncome())<0 )
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new MidCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            else
            {
                assignedLimit = creditLimitAssignService.assignCreditLimit(new HighCreditLimitService(userCreditDto.getMontlyIncome()));
            }
            userCreditDto.setCreditStatus(CreditApprovalStatus.APPROVED.getApprovalStatus());
            java.util.Date approvalDate=new java.util.Date();
            userCreditDto.setCreditApprovalDate(approvalDate);
        }
        else
        {
            userCreditDto.setCreditStatus(CreditApprovalStatus.REJECTED.getApprovalStatus());
        }
        userCreditDto.setCreditAmount(assignedLimit);
        saveCreditAndCreditDetail(userCreditDto);
        log.info(userCreditDto.getUserTckn().toString()+" "+ CreditsConstans.getCreditLimitResult());
        consumer.publishCalculateCreditScoreEvent(userCreditDto.getUserTckn().toString());
        return userCreditDto;
    }


    public void saveCreditAndCreditDetail(UserCreditDto userCreditDto)
    {
        try
        {
            CreditDto creditDto= UserCreditConverter.INSTANCE.userCreditDtoConvertToCreditDto (userCreditDto);
            creditDto=save(creditDto);
            CreditDetailDto creditDetailDto= UserCreditDetailConverter.INSTANCE.userCreditDtoConvertToCreditDetailDto(userCreditDto);
            creditDetailDto.setCredit(creditDto);
            creditDetailService.save(creditDetailDto);
        }
        catch (Exception ex)
        {
            log.error("Kredi işlemi kayıt sırasında hata alındığı için user bilgisi silindi");
            userService.deleteById(userCreditDto.getUserTckn());
            log.error("Kredi işlemi kayıt sırasında hata alındığı için user bilgisi silindi \n MethodName:saveCreditAndCreditDetail");
            throw new BusinessException("işlem başarısız oldu, yeniden deneyin.");
        }

    }

    public Credit findCreditApprovalByTcknAndBirthDate(Long tckn,LocalDate birthDate)
    {
        Credit credit=creditEntityService.findUserByTcknAndBirthDate(tckn,birthDate);
        if(credit==null)
        {
            log.error("NOTFoundException: called method:\t findCreditApprovalByTcknAndBirthDate \n param1:"+tckn+" \n param2 "+birthDate );
            throw new NotFoundException("user-not-found this tckn"+tckn+" and this birthDate"+birthDate, tckn);
        }
        return credit;
    }
}
