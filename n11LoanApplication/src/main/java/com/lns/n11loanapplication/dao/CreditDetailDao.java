package com.lns.n11loanapplication.dao;


import com.lns.n11loanapplication.data.entity.CreditDetail;
import com.lns.n11loanapplication.repository.CreditDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditDetailDao {

    @Autowired
    CreditDetailRepository creditDetailRepository;

    public List<CreditDetail> findAll()
    {
        return  creditDetailRepository.findAll();
    }

    public CreditDetail findByCreditId(Long creditId)
    {
        Optional<CreditDetail> creditDetail =creditDetailRepository.findAllByCreditId(creditId);
        return creditDetail.orElse(null);
    }

    public CreditDetail save(CreditDetail creditDetail)
    {
        return creditDetailRepository.save(creditDetail);
    }
}

