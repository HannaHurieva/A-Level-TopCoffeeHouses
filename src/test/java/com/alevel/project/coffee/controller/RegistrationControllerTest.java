package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.dto.CaptchaResponseDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegistrationControllerTest {

    @MockBean
    private RestTemplate restTemplate;

    private CaptchaResponseDto response = mock(CaptchaResponseDto.class);

    @Autowired
    private RegistrationController registrationController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnRegistrationPageTest() throws Exception {
        this.mockMvc.perform(get("/registration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Registration form")));
    }

    @Test
    public void shouldRedirectToLoginPageIfRegistrationSuccessfullyTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                        .param("username", "User2")
                        .param("firstName", "Name")
                        .param("lastName", "Surname")
                        .param("password", "2")
                        .param("confirmPassword", "2")
                        .param("email", "user2@alevel.com")
                        .param("g-recaptcha-response", "")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void shouldRedirectToLoginPageIfRegistrationSuccessfullyWithoutFirstNameAndLastNameTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                        .param("username", "User3")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("password", "3")
                        .param("confirmPassword", "3")
                        .param("email", "user3@alevel.com")
                        .param("g-recaptcha-response", "")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    public void shouldReturnErrorMessageUsernameCannotBeEmptyTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                        .param("username", "")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "user4@alevel.com")
                        .param("password", "4")
                        .param("confirmPassword", "4")
                        .param("g-recaptcha-response", "")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[1]/div/div").exists())
                .andExpect(content().string(containsString("Username cannot be empty")));
    }

    @Test
    public void shouldReturnErrorMessageUsernameIsExistTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart =
                (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                        .param("username", "User1")
                        .param("firstName", "")
                        .param("lastName", "")
                        .param("email", "user4@alevel.com")
                        .param("password", "4")
                        .param("confirmPassword", "4")
                        .param("g-recaptcha-response", "")
                        .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[1]/div/div").exists())
                .andExpect(content().string(containsString("User already exists!")));
    }

    @Test
    public void shouldReturnErrorMessageEmailCannotBeEmptyTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User4")
                .param("firstName", "")
                .param("lastName", "")
                .param("email", "")
                .param("password", "4")
                .param("confirmPassword", "4")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[4]/div/div").exists())
                .andExpect(content().string(containsString("Email cannot be empty")));
    }

    @Test
    public void shouldReturnErrorMessageEmailIsExistTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User4")
                .param("firstName", "")
                .param("lastName", "")
                .param("email", "user@alevel.com")
                .param("password", "4")
                .param("confirmPassword", "4")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[4]/div/div").exists())
                .andExpect(content().string(containsString("Email already exists!")));
    }

    @Test
    public void shouldReturnErrorMessagePasswordsAreDifferentTest() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User4")
                .param("firstName", "")
                .param("lastName", "")
                .param("email", "user4@alevel.com")
                .param("password", "4")
                .param("confirmPassword", "44")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[5]/div/div").exists())
                .andExpect(content().string(containsString("Passwords are different!")));
    }


    @Test
    public void shouldReturnErrorMessagePasswordConfirmCannotBeEmpty() throws Exception {
        mockCaptcha();

        MockMultipartHttpServletRequestBuilder multipart = (MockMultipartHttpServletRequestBuilder) multipart("/registration")
                .param("username", "User4")
                .param("firstName", "")
                .param("lastName", "")
                .param("email", "user4@alevel.com")
                .param("password", "4")
                .param("confirmPassword", "")
                .param("g-recaptcha-response", "")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(xpath("/html/body/div[1]/form/div[6]/div/div").exists())
                .andExpect(content().string(containsString("Password confirmation cannot be empty")));
    }

    private void mockCaptcha() {
        Mockito.doReturn(response)
                .when(restTemplate)
                .postForObject(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.any()
                );

        Mockito.doReturn(true)
                .when(response)
                .isSuccess();
    }
}
