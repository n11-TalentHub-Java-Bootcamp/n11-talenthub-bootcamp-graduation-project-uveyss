package com.lns.n11loanapplication.converter;


import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreditConverter {
    UserCreditConverter INSTANCE = Mappers.getMapper(UserCreditConverter.class);

    @Mapping(source = "creditId.userTckn",target = "userTckn")
    @Mapping(source = "creditId.birthDate",target = "birthDate")
    @Mapping(source = "creditId.requestDate",target = "requestDate")
    @Mapping(source = "creditId.creditStatus",target = "creditStatus")
    UserCreditDto creditDetailConvertToUserCreditDto(CreditDetail creditDetail);

    @Mapping(target = "creditId.userTckn",source = "userTckn")
    @Mapping(target = "creditId.birthDate",source = "birthDate")
    @Mapping(target = "creditId.requestDate",source = "requestDate")
    @Mapping(target = "creditId.creditStatus",source = "creditStatus")
    CreditDetail userCreditDtoConvertToCreditDetail(UserCreditDto userCreditDto);

    List<CreditDetail> userCreditDtoListConvertToCreditDetailList(List<UserCreditDto> userCreditDto);

    List<UserCreditDto> creditDetailListConvertToUserCreditDtoList(List<CreditDetail> creditDetails);

//TODO solid prensipleri gereğince kullanmayacağın interface i ayrıştır.


    UserCreditDto creditConvertToUserCreditDto(Credit creditDetail);


    Credit userCreditDtoConvertToCredit(UserCreditDto userCreditDto);

    List<Credit> userCreditDtoListConvertToCreditList(List<UserCreditDto> userCreditDto);

    List<UserCreditDto> creditistConvertToUserCreditDtoList(List<Credit> creditDetails);


}