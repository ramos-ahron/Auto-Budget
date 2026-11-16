package com.example.budgetGenerator.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.budgetGenerator.entity.User;
import com.example.budgetGenerator.service.BudgetService;
import com.example.budgetGenerator.service.LLMService;
import com.example.budgetGenerator.service.MailService;
import com.example.budgetGenerator.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UserService userService;
    @MockitoBean
    private BudgetService budgetService;
    @MockitoBean
    private MailService mailService;
    @MockitoBean
    private LLMService llmService;

    private final User user = new User("user@example.com", "secret");

    @BeforeEach
    void setUp() throws Exception {
        when(userService.getUser("user@example.com")).thenReturn(user);
    }

    @Test
    void deleteRejectsWrongPassword() throws Exception {
        mvc.perform(delete("/user/delete").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\",\"password\":\"wrong\"}"))
            .andExpect(status().isConflict());
        verify(userService, never()).deleteUser(any());
    }

    @Test
    void deleteAcceptsCorrectPassword() throws Exception {
        mvc.perform(delete("/user/delete").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\",\"password\":\"secret\"}"))
            .andExpect(status().isOk());
        verify(userService).deleteUser(user);
    }

    @Test
    void updatePasswordRejectsMissingCurrentPassword() throws Exception {
        mvc.perform(post("/user/updatePassword").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\",\"password\":\"new-pass\"}"))
            .andExpect(status().isConflict());
        verify(userService, never()).saveUser(any());
    }

    @Test
    void updatePasswordAcceptsCorrectCurrentPassword() throws Exception {
        mvc.perform(post("/user/updatePassword").contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"user@example.com\",\"password\":\"new-pass\",\"currentPassword\":\"secret\"}"))
            .andExpect(status().isOk());
        verify(userService).saveUser(user);
    }
}
