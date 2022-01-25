package com.lns.n11loanapplication.service.creditLimit;

import com.lns.n11loanapplication.data.constants.CreditsConstans;

import java.math.BigDecimal;

public class HighCreditLimitService extends CreditLimit{
private BigDecimal calculatedCreditLimit;


    public HighCreditLimitService(BigDecimal monthlyIncome) {
        super(monthlyIncome);
    }

    @Override
    public BigDecimal calculateCreditLimit(BigDecimal monthlyIncome) {
        BigDecimal creditLimitRate = CreditsConstans.getCreditLimitRate();
        BigDecimal creditLimitRange =CreditsConstans.getCreditLimitRange();
        calculatedCreditLimit=super.getMonthlyIncome().multiply(creditLimitRate).divide(creditLimitRange);
        if(super.getCollateralAmount()!=null && getCollateralAmount().compareTo(BigDecimal.ZERO)>0)
        {
            calculatedCreditLimit=calculatedCreditLimit.add(calculateCreditLimitWithColleteral(super.getCollateralAmount()));
        }
        return calculatedCreditLimit;
    }

    @Override
    public BigDecimal calculateCreditLimitWithColleteral(BigDecimal creditLimitAmount) {
        BigDecimal highLevelCollateralRange=CreditsConstans.getHighLevelCollateralRate();
         calculatedCreditLimit=calculatedCreditLimit.add(calculatedCreditLimit.multiply(highLevelCollateralRange));
         return calculatedCreditLimit;
    }
}
