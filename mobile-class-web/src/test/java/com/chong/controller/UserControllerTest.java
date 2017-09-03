package com.chong.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 用户控制器测试
 * Created by LXChild on 05/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.content().json("[\n" +
                        "  {\n" +
                        "    \"id\": 1,\n" +
                        "    \"name\": \"123\",\n" +
                        "    \"password\": \"123\",\n" +
                        "    \"mobile\": null,\n" +
                        "    \"email\": null,\n" +
                        "    \"enable\": null,\n" +
                        "    \"realName\": null,\n" +
                        "    \"createTime\": null,\n" +
                        "    \"updateTime\": null,\n" +
                        "    \"avatar\": null\n" +
                        "  }\n" +
                        "]"));
    }
}