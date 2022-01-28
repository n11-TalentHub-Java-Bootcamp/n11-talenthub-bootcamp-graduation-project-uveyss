package com.lns.n11loanapplication.repository;

import com.lns.n11loanapplication.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,Long> {

    User findByUserTckn(Long tckn);


    void deleteByUserTckn(Long tckn);
}
