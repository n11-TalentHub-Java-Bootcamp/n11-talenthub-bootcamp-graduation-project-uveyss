package com.lns.n11loanapplication.data.dto;

import com.lns.n11loanapplication.data.entity.Credit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDetailDto {

    private Long creditDetailId;
    private BigDecimal creditAmount;
    private BigDecimal colleteralAmount;
    private int creditScore;
    private Date creditApprovalDate;
    private CreditDto credit;


    public CreditDto getCredit() {
        return credit;
    }

    public void setCredit(CreditDto credit) {
        this.credit = credit;
    }

    public Long getCreditDetailId() {
        return creditDetailId;
    }

    public void setCreditDetailId(Long creditDetailId) {
        this.creditDetailId = creditDetailId;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getColleteralAmount() {
        return colleteralAmount;
    }

    public void setColleteralAmount(BigDecimal colleteralAmount) {
        this.colleteralAmount = colleteralAmount;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public Date getCreditApprovalDate() {
        return creditApprovalDate;
    }

    public void setCreditApprovalDate(Date creditApprovalDate) {
        this.creditApprovalDate = creditApprovalDate;
    }
}
