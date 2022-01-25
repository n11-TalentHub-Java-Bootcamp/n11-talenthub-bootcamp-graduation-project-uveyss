package com.lns.n11loanapplication.service.creditLimit;

import java.math.BigDecimal;

public interface ICalculateLimit {

     BigDecimal calculateCreditLimit(BigDecimal monthlyIncome);

     BigDecimal calculateCreditLimitWithColleteral(BigDecimal creditLimitAmount);
}
