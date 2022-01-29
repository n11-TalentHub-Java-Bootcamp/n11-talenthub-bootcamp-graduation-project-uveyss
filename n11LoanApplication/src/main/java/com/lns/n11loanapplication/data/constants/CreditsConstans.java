package com.lns.n11loanapplication.data.constants;

import java.math.BigDecimal;

public  class CreditsConstans
{
    private static final BigDecimal lowCreditLimit= BigDecimal.valueOf(10000);
    private static final BigDecimal midCreditLimit= BigDecimal.valueOf(20000);
    private static final BigDecimal lowLevelCollateralRate= BigDecimal.valueOf(0.10);
    private static final BigDecimal midLevelCollateralRate= BigDecimal.valueOf(0.20);
    private static final BigDecimal creditLimitRate= BigDecimal.valueOf(4);
    private static final BigDecimal highLevelCollateralRate = BigDecimal.valueOf(0.25);
    private static final BigDecimal creditLimitRange = BigDecimal.valueOf(2);
    private static final BigDecimal lowMonthlyIncome = BigDecimal.valueOf(5000);
    private static final BigDecimal midMonthlyIncome = BigDecimal.valueOf(10000);
    private static final String creditLimitResultMessage="kredi başvurunuz olumlu sonuçlanmıştır. \n Limitiniz: ";
    private static final String creditLimitResult="tckn ye Kredi limiti atanmıştır. ";
    private static final String kafkaAdminMail ="doganvey@outlook.com";
    private static final String smsDidNotSend ="Kredi Onay bilgisi müşteriye gönderilemedi. müşteri telefon no: ";

    public static BigDecimal getMidMonthlyIncome() {
        return midMonthlyIncome;
    }

    public static BigDecimal getLowMonthlyIncome() {
        return lowMonthlyIncome;
    }

    public static String getKafkaAdminMail() {
        return kafkaAdminMail;
    }

    public static String getSmsDidNotSend() {
        return smsDidNotSend;
    }

    public static String getCreditLimitResultMessage() {
        return creditLimitResultMessage;
    }

    public static String getCreditLimitResult() {
        return creditLimitResult;
    }

    public static BigDecimal getLowCreditLimit() {
        return lowCreditLimit;
    }

    public static BigDecimal getMidCreditLimit() {
        return midCreditLimit;
    }

    public static BigDecimal getLowLevelCollateralRate() {
        return lowLevelCollateralRate;
    }

    public static BigDecimal getMidLevelCollateralRate() {
        return midLevelCollateralRate;
    }

    public static BigDecimal getCreditLimitRate() {
        return creditLimitRate;
    }

    public static BigDecimal getHighLevelCollateralRate() {
        return highLevelCollateralRate;
    }

    public static BigDecimal getCreditLimitRange() {
        return creditLimitRange;
    }
}
