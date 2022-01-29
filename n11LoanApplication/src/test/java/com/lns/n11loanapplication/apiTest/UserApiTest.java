package com.lns.n11loanapplication.apiTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.lns.n11loanapplication.data.dto.UserCreditDto;
import com.lns.n11loanapplication.data.dto.UserDto;
import com.lns.n11loanapplication.data.entity.User;
import com.lns.n11loanapplication.testConfig.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;


public class UserApiTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getUsers() throws Exception {
        String uri = "/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] users = super.mapFromJson(content, User[].class);
        assertTrue(users.length > 0);
    }


    @Test
    public void createProduct() throws Exception {
        String uri = "/users/";
        UserDto userDto = new UserDto();
        userDto.setUserName("uveys");
        userDto.setUserPhone(Long.valueOf("5357479474"));
        userDto.setUserTckn(Long.valueOf("29641522832"));
        userDto.setBirthDate(LocalDate.of(1995,01,04));
        userDto.setFullName("Veysel Doğan");
        userDto.setColleteralAmount(BigDecimal.valueOf(5000));
        String inputJson = super.mapToJson(userDto);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "User is created successfully");
    }
}
