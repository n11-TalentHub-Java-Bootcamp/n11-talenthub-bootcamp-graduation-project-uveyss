package com.lns.n11loanapplication.repository;

import com.lns.n11loanapplication.data.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Repository
public interface CreditRepository  extends JpaRepository<Credit,Long> {

    Credit findCreditByUserTcknAndBirthDate(Long tckn, LocalDate birthDate);
    Credit findCreditByUserTckn(Long tckn);
}

