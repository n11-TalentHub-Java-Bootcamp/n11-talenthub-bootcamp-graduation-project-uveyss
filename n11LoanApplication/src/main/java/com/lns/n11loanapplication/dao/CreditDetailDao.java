package com.lns.n11loanapplication.dao;


import com.lns.n11loanapplication.data.entity.CreditDetail;
import com.lns.n11loanapplication.repository.CreditDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditDetailDao {

    @Autowired
    CreditDetailRepository creditDetailRepository;


    public CreditDetail findByCreditId(Long creditId)
    {
        return creditDetailRepository.findByCredit(creditId);
    }

    public CreditDetail save(CreditDetail creditDetail)
    {
        return creditDetailRepository.save(creditDetail);
    }
}

