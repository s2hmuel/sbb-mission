package com.ll.sbbmission.user.controller;

import com.ll.sbbmission.user.entity.UserCreateForm;
import com.ll.sbbmission.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testSignupValidForm() {
        // Given
        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setUsername("testuser");
        userCreateForm.setEmail("testuser@example.com");
        userCreateForm.setPassword1("password123");
        userCreateForm.setPassword2("password123");

        // When
        String result = userController.signup(userCreateForm, mock(BindingResult.class));

        // Then
        assertEquals("redirect:/", result);
        verify(userService).create("testuser", "testuser@example.com", "password123");
    }

    @Test
    public void testSignupFormWithErrors() {

        UserCreateForm userCreateForm = new UserCreateForm();

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);


        String result = userController.signup(userCreateForm, bindingResult);


        assertEquals("signup_form", result);
        verifyNoInteractions(userService);
    }

    @Test
    public void testSignupFormPasswordsNotMatching() {

        UserCreateForm userCreateForm = new UserCreateForm();
        userCreateForm.setPassword1("password123");
        userCreateForm.setPassword2("password456");

        BindingResult bindingResult = mock(BindingResult.class);


        String result = userController.signup(userCreateForm, bindingResult);

        assertEquals("signup_form", result);
        verify(bindingResult).rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
        verifyNoInteractions(userService);
    }
}