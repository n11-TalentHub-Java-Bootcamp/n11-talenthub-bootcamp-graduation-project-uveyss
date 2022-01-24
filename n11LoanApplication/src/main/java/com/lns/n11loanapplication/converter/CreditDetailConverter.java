package com.lns.n11loanapplication.converter;


import com.lns.n11loanapplication.data.dto.CreditDetailDto;
import com.lns.n11loanapplication.data.entity.CreditDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditDetailConverter {

    CreditDetailConverter INSTANCE = Mappers.getMapper(CreditDetailConverter.class);


    CreditDetailDto creditDetaiConvertToCreditDetaiDto(CreditDetail creditDetail);

    CreditDetail creditDetailDtoConvertToCreditDetail(CreditDetailDto creditDetailDto);

}
