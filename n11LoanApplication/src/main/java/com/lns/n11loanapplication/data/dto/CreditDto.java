package com.lns.n11loanapplication.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditDto {

    private Long creditId;
    private Date requestDate;
    private Long userTckn;
    private Date birthDate;
    private byte creditStatus;

    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Long getUser() {
        return userTckn;
    }

    public void setUser(Long user) {
        this.userTckn = user;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public byte getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(byte creditStatus) {
        this.creditStatus = creditStatus;
    }
}
