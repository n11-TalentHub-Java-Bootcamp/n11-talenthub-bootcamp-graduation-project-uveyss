package com.lns.n11loanapplication.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreditDto implements Serializable {
    @NotNull
    private Long userTckn;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date requestDate;
    @NotNull
    private BigDecimal creditAmount;

    private BigDecimal colleteralAmount=BigDecimal.ZERO;


    @NotNull
    private byte creditStatus;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date creditApprovalDate;
    @NotNull
    private Long creditScore ;
    @NotNull
    private BigDecimal montlyIncome;
    @NotNull
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

    @Override
    public String toString() {
        return "UserCreditDto{" +
                "userTckn=" + userTckn +
                ", birthDate=" + birthDate +
                ", requestDate=" + requestDate +
                ", creditAmount=" + creditAmount +
                ", colleteralAmount=" + colleteralAmount +
                ", creditStatus=" + creditStatus +
                ", creditApprovalDate=" + creditApprovalDate +
                ", creditScore=" + creditScore +
                ", montlyIncome=" + montlyIncome +
                ", userPhone=" + userPhone +
                '}';
    }

}
