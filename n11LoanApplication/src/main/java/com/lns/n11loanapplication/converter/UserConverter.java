package com.lns.n11loanapplication.converter;

import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);


    UserDto userConvertToUserDto(User user);
    User userDtoConvertToUser(UserDto userDto);
    List<UserDto> userListConvertToUserDtoList(List<User> users);
    List<User> userDtoConvertToUser(List<UserDto> userDtoList);
    UserDto userCreditDtoConvertToUserDto (UserCreditDto userCreditDto);
    UserCreditDto userDtoConvertToUserCreditDto (UserDto userDto);





}
