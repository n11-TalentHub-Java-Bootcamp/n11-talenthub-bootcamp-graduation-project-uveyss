package com.lns.n11loanapplication.service.creditLimit;

import com.lns.n11loanapplication.data.constants.CreditsConstans;
import java.math.BigDecimal;

public class MidCreditLimitService extends CreditLimit{


    public MidCreditLimitService(BigDecimal monthlyIncome) {
        super(monthlyIncome);
    }
    private BigDecimal calculatedCreditLimit;
    @Override
    public BigDecimal calculateCreditLimit(BigDecimal monthlyIncome) {
        calculatedCreditLimit= CreditsConstans.getMidCreditLimit();
        if(super.getCollateralAmount()!=null && getCollateralAmount().compareTo(BigDecimal.ZERO)>0)
        {
            calculatedCreditLimit=calculatedCreditLimit.add(calculateCreditLimitWithColleteral(super.getCollateralAmount()));
        }
        return calculatedCreditLimit;
    }

    @Override
    public BigDecimal calculateCreditLimitWithColleteral(BigDecimal creditLimitAmount) {
        BigDecimal highLevelCollateralRange=CreditsConstans.getMidLevelCollateralRate();
        calculatedCreditLimit=calculatedCreditLimit.add(calculatedCreditLimit.multiply(highLevelCollateralRange));
        return calculatedCreditLimit;
    }
}
