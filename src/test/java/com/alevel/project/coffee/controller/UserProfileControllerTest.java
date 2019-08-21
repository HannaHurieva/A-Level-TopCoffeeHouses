package com.alevel.project.coffee.controller;

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
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@WithUserDetails(value = "User1")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserProfileController userProfileController;

    @Test
    public void navbarShouldContainsUserNameTest() throws Exception {
        this.mockMvc.perform(get("/user/profile/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/div/h5")
                        .string(containsString("User1")));
    }

    @Test
    public void pageShouldContainsUsernameTest() throws Exception {
        this.mockMvc.perform(get("/user/profile/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("User1")));
    }

    @Test
    public void pageShouldContainsFirstNameTest() throws Exception {
        this.mockMvc.perform(get("/user/profile/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Name")));
    }

    @Test
    public void pageShouldContainsLastNameTest() throws Exception {
        this.mockMvc.perform(get("/user/profile/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("Surname")));
    }

    @Test
    public void pageShouldContainsEmailTest() throws Exception {
        this.mockMvc.perform(get("/user/profile/2"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(content().string(containsString("user@alevel.com")));
    }

    @Test
    public void shouldRedirectWhenChangeFirstNameTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user/profile/2")
                        .param("firstName", "firstName")
                        .param("lastName", "")
                        .param("password", "")
                        .param("email", "user@alevel.com")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldRedirectWhenChangeLastNameTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user/profile/2")
                        .param("firstName", "")
                        .param("lastName", "lastName")
                        .param("password", "")
                        .param("email", "user@alevel.com")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldRedirectWhenChangePasswordTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user/profile/2")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("password", "password")
                        .param("email", "user@alevel.com")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldRedirectWhenChangeEmailTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user/profile/2")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("password", "")
                        .param("email", "user.email@email.com")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void shouldReturnEmailErrorMessageIsCannotBeEmptyTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user/profile/2")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("password", "")
                        .param("email", "")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div/form/div[4]/div/div").exists())
                .andExpect(content().string(containsString("Email cannot be empty")));
    }
}
