package com.lns.n11loanapplication.apiTest;

import com.lns.n11loanapplication.data.entity.Credit;
import com.lns.n11loanapplication.data.entity.User;
import com.lns.n11loanapplication.testConfig.AbstractTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreditsApiTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void getCredits() throws Exception {
       String uri = "/credits";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Credit[] credits = super.mapFromJson(content, Credit[].class);
        assertTrue(credits.length > 0);
    }

    @Test
    public void getCreditsInquiry() throws Exception {

       String uri = "/credits/44444444599/1995-01-29";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Credit[] credits = super.mapFromJson(content, Credit[].class);
        assertTrue(credits.length > 0);
    }



}
