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
@WithUserDetails("admin")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserAdministrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAdministrationController userAdministrationController;

    @Test
    public void adminNavbarShouldContainsUserListTest() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("/html/body/nav/div/ul/li[3]/a").string("User list"));
    }

    @Test
    public void pageShouldContainsListOfUsersTest() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("List of users")))
                .andExpect(xpath("/html/body/div/table/tbody/tr").nodeCount(2));
    }

    @Test
    public void adminRecordShouldContainsTwoRolesTest() throws Exception {
        this.mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(content().string(containsString("List of users")))
                .andExpect(xpath("/html/body/div")
                        .string(containsString("ADMIN")))
                .andExpect(xpath("/html/body/div")
                        .string(containsString("USER")));
    }

    @Test
    public void userRecordShouldNotContainsAdminRoleTest() throws Exception {
        this.mockMvc.perform(get("/user/2"))
                .andDo(print())
                .andExpect(xpath("//*[@id=\"ADMIN\"]").booleanValue(false));
    }

    @Test
    public void shouldRedirectFromUserEditorPageWhenChangeUsernameTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user")
                        .param("userId", "2")
                        .param("username", "User2")
                        .param("roles", "USER")
                        .param("status", "ACTIVE")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void shouldRedirectFromUserEditorPageWhenChangeUserRoleOnAdminRoleTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user")
                        .param("userId", "2")
                        .param("username", "User1")
                        .param("roles", "ADMIN")
                        .param("status", "ACTIVE")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void shouldRedirectFromUserEditorPageWhenChoosingTwoRolesTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user")
                        .param("userId", "2")
                        .param("username", "User1")
                        .param("roles", "USER", "ADMIN")
                        .param("status", "ACTIVE")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void shouldRedirectFromUserEditorPageWhenChangeStatusToDisabledTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user")
                        .param("userId", "2")
                        .param("username", "User1")
                        .param("roles", "USER")
                        .param("status", "NOT_ACTIVE")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }

    @Test
    public void shouldRedirectFromUserEditorPageWhenChangeStatusToLockedTest() throws Exception {
        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/user")
                        .param("userId", "2")
                        .param("username", "User1")
                        .param("roles", "USER")
                        .param("status", "DELETED")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user"));
    }
}
