package com.ll.sbbmission.user.service;

import com.ll.sbbmission.user.entity.SiteUser;
import com.ll.sbbmission.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;



    @Test
    public void testCreateUser() {
        // Given
        String username = "testuser";
        String email = "testuser@example.com";
        String password = "testpassword";

        // Mock PasswordEncoder.encode의 반환값 설정
        when(passwordEncoder.encode(password)).thenReturn("hashedPassword");

        // When
        SiteUser createdUser = userService.create(username, email, password);

        // Then
        assertNotNull(createdUser);
        verify(userRepository).save(any(SiteUser.class));
        verify(passwordEncoder).encode(password);
    }

    @Test
    void TestGetUser() {
        // Arrange
        String username = "testUser";
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);

        when(userRepository.findByusername(username)).thenReturn(Optional.of(siteUser));

        // Act
        SiteUser result = userService.getUser(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
    }
}
