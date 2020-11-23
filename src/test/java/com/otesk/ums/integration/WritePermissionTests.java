package com.otesk.ums.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class WritePermissionTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUserAccountTest() throws Exception {
        mockMvc.perform(post("/user/new")
                .param("username", "createduser")
                .param("firstName", "Nikolai")
                .param("lastName", "Davidov")
                .param("password", "test1234")
                .param("confirmPassword", "test1234")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("createduser")));
    }

    @Test
    public void editUserAccountTest() throws Exception {
        mockMvc.perform(put("/user/2/edit")
                .param("username", "edituser")
                .param("firstName", "Jack")
                .param("lastName", "Reader")
                .param("password", "testpass1234")
                .param("confirmPassword", "testpass1234")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("edituser")));
    }

    @Test
    public void deleteUserAccountTest() throws Exception {
        mockMvc.perform(delete("/user/3")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(xpath("//tbody[@id='userList']/tr").nodeCount(2));
    }

    @Test
    public void changeStatusOfUserTest() throws Exception {
        mockMvc.perform(put("/user/3")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/3"));
        mockMvc.perform(get("/user/3"))
                .andDo(print())
                .andExpect(content().string(containsString("ACTIVE")));
    }
}
