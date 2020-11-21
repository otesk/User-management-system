package com.otesk.ums;

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

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ReadPermissionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userAccountListTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(xpath("//tbody[@id='userList']/tr").nodeCount(3));
    }

    @Test
    public void filteredUserAccountListByUsernameTest() throws Exception {
        mockMvc.perform(get("/user").param("usernameForSearchFilter", "lock"))
                .andDo(print())
                .andExpect(xpath("//tbody[@id='userList']/tr").exists());
    }

    @Test
    public void filteredUserAccountListByRoleTest() throws Exception {
        mockMvc.perform(get("/user").param("USER", "on"))
                .andDo(print())
                .andExpect(xpath("//tbody[@id='userList']/tr").nodeCount(2));
    }

    @Test
    public void userAccountPageTest() throws Exception {
        mockMvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(xpath("//tbody[@id='userPage']/tr").exists());
    }

    @Test
    public void logoutTest() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}
