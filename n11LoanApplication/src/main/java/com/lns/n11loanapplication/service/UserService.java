package com.lns.n11loanapplication.service;

import com.lns.n11loanapplication.api.errorHandling.exception.BusinessException;
import com.lns.n11loanapplication.converter.UserConverter;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.User;
import com.lns.n11loanapplication.service.entityService.UserEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@Slf4j
public class UserService {
@Autowired
    UserEntityService userEntityService;

    public List<UserDto> findAll()
    {
        //todo : NULL KONTROLÜ
        List<User> userList =userEntityService.findAll();
        return UserConverter.INSTANCE.userListConvertToUserDtoList(userList);
    }

    public UserDto findByUserTckn(Long tckn)
    {
        User user =userEntityService.findByUserTckn(tckn);
        if(user==null)
        {
            log.info("user not found. Method name:findByUserTckn \n param:"+tckn);
            throw new BusinessException("user-not-found", tckn);

        }
        return UserConverter.INSTANCE.userConvertToUserDto(user);


    }

    public UserDto save (UserDto userDto)
    {
        try
        {
           User user = UserConverter.INSTANCE.userDtoConvertToUser(userDto);
           user = userEntityService.save(user);
           return UserConverter.INSTANCE.userConvertToUserDto(user);
        }
        catch (Exception ex)
        {
            log.error("save or update exception in UserService",ex);
            throw new BusinessException("UserService's save methods has an exception ",ex.getLocalizedMessage());

        }
    }

    public void delete (Long id)
    {
        User user = userEntityService.findUserById(id);
        if(user==null)
        {
            throw new BusinessException("user-not-found", id.toString());
        }
        userEntityService.deletebyId(id);
    }

    public UserCreditDto findUserForCreditByTckn(Long tckn)
    {
        UserDto userDto = findByUserTckn(tckn);
        return UserConverter.INSTANCE.userDtoConvertToUserCreditDto(userDto);
    }
}
