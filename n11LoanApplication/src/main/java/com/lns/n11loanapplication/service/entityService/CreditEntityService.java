package com.lns.n11loanapplication.service.entityService;


import com.lns.n11loanapplication.dao.CreditDao;
import com.lns.n11loanapplication.data.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreditEntityService {
    @Autowired
    CreditDao creditDao;

    public Credit findUserByTcknAndBirthDate(Long tckn, Date birthDate)
    {
       return creditDao.findByTcknAndBirthDate(tckn,birthDate);
    }

    public Credit save (Credit credit)
    {
        return  creditDao.save(credit);
    }
}
