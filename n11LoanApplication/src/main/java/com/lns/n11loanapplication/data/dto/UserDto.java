package com.lns.n11loanapplication.data.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    @NotNull
    private Long userTckn;
    @NotNull
    private String userName;
    @NotNull
    private String fullName;

    @NotNull
    private Long userPhone;
    @NotNull
    private Date recordDate;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private BigDecimal montlyIncome;

    private BigDecimal colleteralAmount;

    public BigDecimal getMontlyIncome() {
        return montlyIncome;
    }

    public void setMontlyIncome(BigDecimal montlyIncome) {
        this.montlyIncome = montlyIncome;
    }

    public BigDecimal getColleteralAmount() {
        return colleteralAmount;
    }

    public void setColleteralAmount(BigDecimal colleteralAmount) {
        this.colleteralAmount = colleteralAmount;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getUserTckn() {
        return userTckn;
    }

    public void setUserTckn(Long userTckn) {
        this.userTckn = userTckn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(Long userPhone) {
        this.userPhone = userPhone;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
}