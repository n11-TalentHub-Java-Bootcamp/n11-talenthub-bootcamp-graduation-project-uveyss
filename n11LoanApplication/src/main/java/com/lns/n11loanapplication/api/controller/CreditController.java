package com.lns.n11loanapplication.api.controller;

import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/credit/")
public class CreditController {
    @Autowired
    CreditService creditService;



    @GetMapping("/{tckn}/{birthDate}/")
    public ApiResponse findUserCredit(@PathVariable Long tckn, @PathVariable Date birthDate)
    {
        UserCreditDto userCreditDto= creditService.prepareUserCreditDtoForCreditApproval(tckn,birthDate);
        return ApiResponse.success(userCreditDto);
    }

    @GetMapping("/{tckn}")
    public ApiResponse findUserCredit(@PathVariable Long tckn)
    {
        UserCreditDto userCreditDto= creditService.prepareUserCreditDtoForCreditApproval(tckn.toString());
        return ApiResponse.success(userCreditDto);
    }

}
