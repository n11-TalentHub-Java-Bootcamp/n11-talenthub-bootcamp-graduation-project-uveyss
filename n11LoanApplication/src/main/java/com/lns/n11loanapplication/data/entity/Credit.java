package com.lns.n11loanapplication.data.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Credit" ,schema = "public" )
@SequenceGenerator(schema = "public",name = "generator",sequenceName = "credit_id_seq")
@AllArgsConstructor
@NoArgsConstructor
public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long creditId;

    @Column(name = "requestDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date requestDate;
    @Column(name = "userTckn" ,nullable = false)
    private Long userTckn;
    @Column(name = "birthDate" ,nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    @Column(name = "creditStatus" ,nullable = false)
    private byte creditStatus;

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
        return userTckn;
    }

    public void setUser(Long user) {
        this.userTckn = user;
    }
    public Long getCreditId() {
        return creditId;
    }

    public void setCreditId(Long creditId) {
        this.creditId = creditId;
    }

    public Long getUserTckn() {
        return userTckn;
    }

    public void setUserTckn(Long userTckn) {
        this.userTckn = userTckn;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}

