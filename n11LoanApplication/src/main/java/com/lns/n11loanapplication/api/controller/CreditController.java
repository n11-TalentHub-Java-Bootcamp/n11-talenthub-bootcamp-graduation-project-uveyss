package com.lns.n11loanapplication.api.controller;

import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.service.CreditDetailService;
import com.lns.n11loanapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/credit/")
public class CreditController {
    @Autowired
    CreditService creditService;
    @Autowired
    CreditDetailService creditDetailService;
    @DeleteMapping
    public ApiResponse deleteAll()
    {
         creditService.deleteAll();
        return ApiResponse.success();
    }
    @GetMapping("/{tckn}/{birthDate}")
    public ApiResponse findUserCredit(@PathVariable Long tckn, @PathVariable Date birthDate)
    {
        return ApiResponse.success();
    }

}
