package com.lns.n11loanapplication.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreditDto implements Serializable {
    private Long userTckn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date requestDate;
    private BigDecimal creditAmount;
    private BigDecimal colleteralAmount;
    private byte creditStatus;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date creditApprovalDate;
    private Long creditScore ;
    private BigDecimal montlyIncome;
    private Long userPhone;

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public BigDecimal getMontlyIncome() {
        return montlyIncome;
    }

    public void setMontlyIncome(BigDecimal montlyIncome) {
        this.montlyIncome = montlyIncome;
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
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

    public byte getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(byte creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Date getCreditApprovalDate() {
        return creditApprovalDate;
    }

    public void setCreditApprovalDate(Date creditApprovalDate) {
        this.creditApprovalDate = creditApprovalDate;
    }

    public Long getUserTckn() {
        return userTckn;
    }

    public void setUserTckn(Long userTckn) {
        this.userTckn = userTckn;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }


}
