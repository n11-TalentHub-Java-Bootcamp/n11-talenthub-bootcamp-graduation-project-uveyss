package com.lns.n11loanapplication.api.controller;


import com.lns.n11loanapplication.api.errorHandling.response.ApiResponse;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.engine.Consumer;
import com.lns.n11loanapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {


    @Autowired
    UserService userService;


    private final Consumer consumer;

    public UserController(Consumer consumer) {
        this.consumer = consumer;
    }

    @GetMapping()
    public List<UserDto> findAll()
    {
        return  userService.findAll();
    }

    @GetMapping("/{tckn}")
    public UserDto findByTckn(Long tckn)
    {
        return userService.findByUserTckn(tckn);
    }

    @PostMapping()
    public UserDto save (@RequestBody UserDto userDto)
    {
        userDto=userService.save(userDto);
       consumer.publishCalculateCreditScoreEvent(userDto.getUserTckn().toString());
        return userDto;
    }

    @PutMapping
    public ApiResponse update(@RequestBody UserDto userDto)
    {
        return ApiResponse.success(userService.save(userDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete (@PathVariable Long id)
    {
        userService.delete(id);
        return ApiResponse.success();
    }

}