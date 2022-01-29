package com.lns.n11loanapplication.service.entityService;


import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.dao.CreditDao;
import com.lns.n11loanapplication.data.entity.Credit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class CreditEntityService {
    @Autowired
    CreditDao creditDao;

    public Credit findUserByTcknAndBirthDate(Long tckn, LocalDate birthDate)
    {
       return creditDao.findByTcknAndBirthDate(tckn,birthDate);
    }

    public Credit save (Credit credit)
    {
        return  creditDao.save(credit);
    }

    public Credit findByTckn(Long tckn)
    {
        Credit credit =creditDao.findByTckn(tckn);
        if(credit==null)
        {
            throw new BusinessException("credit-not-found with parameter tckn", tckn.toString());
        }
        return credit;
    }
    public List<Credit> findAll()
    {
        return  creditDao.findAll();
    }
}
