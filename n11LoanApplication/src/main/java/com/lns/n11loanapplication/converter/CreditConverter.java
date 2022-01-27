package com.lns.n11loanapplication.converter;


import com.lns.n11loanapplication.data.dto.CreditDto;
import com.lns.n11loanapplication.data.entity.Credit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditConverter {

    CreditConverter INSTANCE = Mappers.getMapper(CreditConverter.class);
    CreditDto creditConvertToCreditDto(Credit credit);

    Credit creditDtoConvertToCredit(CreditDto creditDto);

}
