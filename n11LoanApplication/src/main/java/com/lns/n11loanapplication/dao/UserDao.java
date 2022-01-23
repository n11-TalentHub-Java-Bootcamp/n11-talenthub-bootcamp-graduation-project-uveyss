package com.lns.n11loanapplication.dao;

import com.lns.n11loanapplication.data.entity.User;
import com.lns.n11loanapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDao {
@Autowired
UserRepository userRepository;

    public List<User> findAll()
    {
        return userRepository.findAll();
    }
    public User save (User user)
    {
        return userRepository.save(user);
    }

    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }

    public User findByUserTckn(Long userTckn)
    {
        return userRepository.findByUserTckn(userTckn);
    }
}
