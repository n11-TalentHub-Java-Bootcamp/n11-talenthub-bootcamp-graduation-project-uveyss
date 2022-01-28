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
import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.service.creditLimit.*;
import com.lns.n11loanapplication.service.entityService.CreditEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
public class CreditService {
    @Autowired
    CreditScoreService creditScoreService;



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

    public UserCreditDto prepareUserCreditDtoForCreditApproval(String userTckn)
    {
        UserCreditDto userCreditDto =userService.findUserForCreditByTckn(Long.valueOf(userTckn));
        Long creditScore = creditScoreService.calculateCreditScore(userCreditDto);
        userCreditDto.setCreditScore(creditScore);
        userCreditDto.setRequestDate(new Date(System.currentTimeMillis()));
        return userCreditDto;
    }

    public UserCreditDto prepareUserCreditDtoForCreditApproval(Long tckn, Date birthDate)
    {
        Credit credit =findCreditApprovalByTcknAndBirthDate(tckn,birthDate);
       CreditDto creditDto =CreditConverter.INSTANCE.creditConvertToCreditDto(credit);
       CreditDetailDto creditDetailDto = creditDetailService.findByCreditId(creditDto.getCreditId());
       UserCreditDto userCreditDto =UserCreditConverter.INSTANCE.creditDtoConvertToUserCreditDto(creditDto);
       userCreditDto.setCreditAmount(creditDetailDto.getCreditAmount());
       userCreditDto.setColleteralAmount(creditDetailDto.getColleteralAmount());
       userCreditDto.setCreditApprovalDate(creditDetailDto.getCreditApprovalDate());
       userCreditDto.setCreditScore(creditDetailDto.getCreditScore());
       return userCreditDto;
    }


    public UserCreditDto calculateCreditLimit(String userTckn){
        UserCreditDto userCreditDto =prepareUserCreditDtoForCreditApproval(userTckn);
        BigDecimal assignedLimit =BigDecimal.ZERO;
        CreditLimitAssignService creditLimitAssignService = CreditLimitAssignService.getInstance();
        if(userCreditDto.getCreditScore()<500)
        {
            userCreditDto.setCreditStatus(CreditApprovalStatus.REJECTED.getApprovalStatus());
            assignedLimit=creditLimitAssignService.assignCreditLimit(new CreditLimit(userCreditDto.getMontlyIncome()));
        }
        else if(userCreditDto.getCreditScore()<1000)
        {

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
        log.info(userCreditDto.getUserTckn().toString()+" "+ CreditsConstans.getCreditLimitResult());
        return userCreditDto;
    }

    @Transactional
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

    public Credit findCreditApprovalByTcknAndBirthDate(Long tckn,Date birthDate)
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
