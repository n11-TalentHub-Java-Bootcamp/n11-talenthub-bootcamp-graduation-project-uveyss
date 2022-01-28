package com.lns.n11loanapplication.service.entityService;

import com.lns.n11loanapplication.dao.UserDao;
import com.lns.n11loanapplication.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityService {

    @Autowired
    UserDao userDao;

    public List<User> findAll()
    {
        return userDao.findAll();
    }

    public User findByUserTckn(Long tckn)
    {
       return userDao.findByUserTckn(tckn);
    }

    public User save (User user)
    {
        return userDao.save(user);
    }

    public void deletebyId (Long id)
    {
        userDao.deleteById(id);
    }

    public User findUserById(Long id)
    {
        return userDao.findUserById(id);
    }

    public void deleteUserByTckn(Long tckn)
    {
        userDao.deleteByTckn(tckn);
    }
}
