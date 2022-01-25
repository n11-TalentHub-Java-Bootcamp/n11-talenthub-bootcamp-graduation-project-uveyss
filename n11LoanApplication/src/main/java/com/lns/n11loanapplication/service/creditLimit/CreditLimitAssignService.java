package com.lns.n11loanapplication.service.creditLimit;

import java.math.BigDecimal;

public class CreditLimitAssignService {

    /*Created based on singleton pattern for access the instance*/

    private CreditLimitAssignService()
    {

    }

    private  static CreditLimitAssignService instance;


    public static CreditLimitAssignService getInstance(){

        if (instance == null){
            instance = new CreditLimitAssignService();
        }

        return instance;
    }
    public BigDecimal assignCreditLimit(CreditLimit creditLimit)
    {
        return creditLimit.calculateCreditLimit(creditLimit.getMonthlyIncome());
    }
}
