package com.lns.n11loanapplication.converter;


import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.dto.CreditDto;
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
    UserCreditDto creditDtoConvertToUserCreditDto(CreditDto creditDto);
    CreditDto userCreditDtoConvertToCreditDto(UserCreditDto userCreditDto);
    List<CreditDto> userCreditDtoListConvertToCreditDtoList(List<UserCreditDto> userCreditDto);
    List<UserCreditDto> creditDtoListConvertToUserCreditDtoList(List<CreditDto> creditDtos);



}