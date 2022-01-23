package com.lns.n11loanapplication.service;


import com.lns.n11loanapplication.converter.UserCreditConverter;
import com.lns.n11loanapplication.dao.CreditDetailDao;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditDetailService {

    @Autowired
    CreditDetailDao creditDetailDao;


    public List<UserCreditDto> findAll()
    {
        List<CreditDetail> userList =creditDetailDao.findAll();
        return UserCreditConverter.INSTANCE.creditDetailListConvertToUserCreditDtoList(userList);
    }

    public UserCreditDto findByCreditId(Long id)
    {
        CreditDetail creditDetail= creditDetailDao.findByCreditId(id);
        return UserCreditConverter.INSTANCE.creditDetailConvertToUserCreditDto(creditDetail);
    }

    public UserCreditDto save (UserCreditDto userCreditDto)
    {
        CreditDetail creditDetail = UserCreditConverter.INSTANCE.userCreditDtoConvertToCreditDetail(userCreditDto);
        creditDetail=creditDetailDao.save(creditDetail);
        return  UserCreditConverter.INSTANCE.creditDetailConvertToUserCreditDto(creditDetail);
    }
}

