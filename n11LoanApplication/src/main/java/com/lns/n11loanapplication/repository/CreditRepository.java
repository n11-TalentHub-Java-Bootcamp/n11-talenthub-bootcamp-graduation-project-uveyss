package com.lns.n11loanapplication.repository;

import com.lns.n11loanapplication.data.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository  extends JpaRepository<Credit,Long> {
}

