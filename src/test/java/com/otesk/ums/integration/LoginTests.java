package com.otesk.ums.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LoginTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, guest!")))
                .andExpect(content().string(containsString("sign in")));
    }

    @Test
    public void accessDeniedTest() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLoginTest() throws Exception {
        mockMvc.perform(formLogin().user("admin").password("admin1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void loginWithoutCredentialsTest() throws Exception {
        mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "us12"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void loginWithBadCredentialsTest() throws Exception {
        mockMvc.perform(formLogin().user("user").password("us12"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(result -> {
                    if (!result.getRequest().getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").toString().contains("Bad credentials"))
                    throw new Exception("The problem is not with incorrect username or password.");
                });
    }

    @Test
    public void loginForLockedUserTest() throws Exception {
        mockMvc.perform(formLogin().user("lockeduser").password("lockuser1234"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?error"))
                .andExpect(result -> {
                    if (!result.getRequest().getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION").toString().contains("User account is locked"))
                        throw new Exception("The problem is not with locked user.");
                });
    }
}
