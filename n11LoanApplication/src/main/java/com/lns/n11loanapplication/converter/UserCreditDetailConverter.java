package com.lns.n11loanapplication.converter;

import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreditDetailConverter {

    UserCreditDetailConverter INSTANCE = Mappers.getMapper(UserCreditDetailConverter.class);
    UserCreditDto creditDetailDtoConvertToUserCreditDto(CreditDetailDto creditDetailDto);
    CreditDetailDto userCreditDtoConvertToCreditDetailDto(UserCreditDto userCreditDto);

}
