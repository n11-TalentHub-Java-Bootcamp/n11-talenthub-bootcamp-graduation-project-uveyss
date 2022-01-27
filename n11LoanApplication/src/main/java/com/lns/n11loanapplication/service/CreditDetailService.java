package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.converter.CreditDetailConverter;
import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import com.lns.n11loanapplication.service.entityService.CreditDetailEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreditDetailService {

    @Autowired
    CreditDetailEntityService creditDetailEntityService;

    public void save (CreditDetailDto userCreditDto)
    {
        try
        {
            CreditDetail creditDetail = CreditDetailConverter.INSTANCE.creditDetailDtoConvertToCreditDetail(userCreditDto);
            creditDetail=creditDetailEntityService.save(creditDetail);
            CreditDetailConverter.INSTANCE.creditDetaiConvertToCreditDetaiDto(creditDetail);
        }
        catch (Exception ex)
        {
            log.error("save or update exception in CreditService",ex);
            throw new BusinessException("CreditDetailService's save methods has an exception ",ex.getLocalizedMessage());

        }

    }

    public CreditDetailDto findByCreditId(Long creditId)
    {
        CreditDetail creditDetail = creditDetailEntityService.findCreditDetailByCreditId(creditId);
        return CreditDetailConverter.INSTANCE.creditDetaiConvertToCreditDetaiDto(creditDetail);
    }

}

