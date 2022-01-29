package com.lns.n11loanapplication.dao;

import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreditDao {
    @Autowired
    CreditRepository creditRepository;

    public List<Credit> findAll()
    {
        return creditRepository.findAll();
    }
    public Credit findByTcknAndBirthDate(Long tckn, LocalDate birthDate)
    {
        return creditRepository.findCreditByUserTcknAndBirthDate(tckn,birthDate);
    }

    public Credit findByTckn(Long tckn)
    {
        return  creditRepository.findCreditByUserTckn(tckn);
    }
    public Credit save (Credit credit)
    {
        return creditRepository.save(credit);
    }


}
