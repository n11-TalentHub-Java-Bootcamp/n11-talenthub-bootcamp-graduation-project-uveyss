package com.lns.n11loanapplication.api.controller;


import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;

import com.lns.n11loanapplication.service.CreditService;
import com.lns.n11loanapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/users/")
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    private CreditService creditService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<UserDto> findAll()
    {
        return  userService.findAll();
    }

    @GetMapping("{tckn}")
    public UserDto findByTckn(@Valid @PathVariable Long tckn)
    {
        return userService.findByUserTckn(tckn);
    }


    @PostMapping()
    public ApiResponse save (@Valid @RequestBody UserDto userDto)
    {
        userDto=userService.save(userDto);
        UserCreditDto userCreditDto=creditService.calculateCreditLimit(userDto);
        return ApiResponse.success(userCreditDto);
    }

    @PutMapping
    public ApiResponse update(@Valid @RequestBody UserDto userDto)
    {
        return ApiResponse.success(userService.save(userDto));
    }


    @PatchMapping
    @CrossOrigin(origins = "*")
    public ApiResponse updateFields(@Valid @RequestBody UserDto userDto)
    {
        return ApiResponse.success(userService.save(userDto));
    }


    @DeleteMapping("{id}")
    @CrossOrigin(origins = "*")
    public ApiResponse delete (@PathVariable String id)
    {
        userService.delete(id);
        return ApiResponse.success();
    }

    /*
    * entity admin tarafından kontrol edilebilmesi adına açıldı.
    * */
    @GetMapping("{id}")
    @CrossOrigin(origins = "*")
    public ApiResponse findById(@PathVariable String id)
    {
        return ApiResponse.success(userService.findUserById(id));
    }

}