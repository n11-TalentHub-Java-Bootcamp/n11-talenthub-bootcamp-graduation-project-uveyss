package com.lns.n11loanapplication.data.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

public class Credit implements Serializable {
    @Id
    @GeneratedValue(generator = "generator")
    private Long creditId;

    @Column(name = "requestDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate;
    @Column(name = "userTckn" ,nullable = false)
    private Long user;
    @Column(name = "birthDate" ,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthDate;


    @Column(name = "creditStatus" ,nullable = false)
    private byte creditStatus;

    @OneToOne(mappedBy = "credit", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CreditDetail creditDetailId;


    public byte getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(byte creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getUserTckn() {
        return user;
    }

    public void setUserTckn(Long userTckn) {
        this.user = userTckn;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}

