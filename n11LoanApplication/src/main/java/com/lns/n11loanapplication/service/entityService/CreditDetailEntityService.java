package com.lns.n11loanapplication.service.entityService;


import com.lns.n11loanapplication.dao.CreditDetailDao;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditDetailEntityService {

    @Autowired
    CreditDetailDao creditDetailDao;




    public CreditDetail save (CreditDetail creditDetail)
    {
       return creditDetailDao.save(creditDetail);
    }

    public CreditDetail findCreditDetailByCreditId(Long id)
    {
        return creditDetailDao.findByCreditId(id);
    }
}
