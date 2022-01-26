package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.converter.CreditDetailConverter;
import com.lns.n11loanapplication.dao.CreditDetailDao;
import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditDetailService {

    @Autowired
    CreditDetailDao creditDetailDao;




    public void save (CreditDetailDto userCreditDto)
    {

        CreditDetail creditDetail = CreditDetailConverter.INSTANCE.creditDetailDtoConvertToCreditDetail(userCreditDto);
        creditDetail=creditDetailDao.save(creditDetail);
        CreditDetailConverter.INSTANCE.creditDetaiConvertToCreditDetaiDto(creditDetail);
    }
}

