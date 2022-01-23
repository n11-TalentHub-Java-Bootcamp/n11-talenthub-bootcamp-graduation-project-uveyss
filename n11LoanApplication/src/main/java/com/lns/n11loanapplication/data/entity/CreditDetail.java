package com.lns.n11loanapplication.data.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "CreditDetail" ,schema = "public" )
@SequenceGenerator(schema = "public",name = "generator",sequenceName = "creditDetail_id_seq")
@AllArgsConstructor
@NoArgsConstructor
public class CreditDetail implements Serializable {

    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "creditDetailId" ,nullable = false)
    private Long creditDetailId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "creditId")
    private Credit credit;

    @Column(name = "creditAmount" ,nullable = false)
    private BigDecimal creditAmount;

    @Column(name = "colleteralAmount" ,nullable = false)
    private BigDecimal colleteralAmount;


    @Column(name = "creditScore" ,nullable = false)
    private Long creditScore;

    @Column(name = "creditApprovalDate" ,nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditApprovalDate;

    public Long getCreditDetailId() {
        return creditDetailId;
    }

    public void setCreditDetailId(Long creditDetailId) {
        this.creditDetailId = creditDetailId;
    }

    public Credit getCreditId() {
        return credit;
    }

    public void setCreditId(Credit creditId) {
        this.credit = creditId;
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

    public Date getCreditApprovalDate() {
        return creditApprovalDate;
    }

    public void setCreditApprovalDate(Date creditApprovalDate) {
        this.creditApprovalDate = creditApprovalDate;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public Long getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Long creditScore) {
        this.creditScore = creditScore;
    }

}

