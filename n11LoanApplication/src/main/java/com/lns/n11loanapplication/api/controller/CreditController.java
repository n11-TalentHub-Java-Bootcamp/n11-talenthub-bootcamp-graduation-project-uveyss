package com.lns.n11loanapplication.api.controller;


import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.service.CreditDetailService;
import com.lns.n11loanapplication.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/credit/")
public class CreditController {
    @Autowired
    CreditService creditService;
    @Autowired
    CreditDetailService creditDetailService;

    @GetMapping
    public List<UserCreditDto> findAll(){
        return creditDetailService.findAll();
    }
    @PostMapping
    public UserCreditDto Save(@RequestBody UserCreditDto userCreditDto){
        return creditService.save(userCreditDto);
    }
}
