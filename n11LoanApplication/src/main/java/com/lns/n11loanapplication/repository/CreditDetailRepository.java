package com.lns.n11loanapplication.repository;

import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditDetailRepository extends JpaRepository<CreditDetail,Long> {

    @Query("select cd from CreditDetail cd where cd.credit.creditId=?1")
    public Optional<CreditDetail> findAllByCreditId(Long creditId);
}
