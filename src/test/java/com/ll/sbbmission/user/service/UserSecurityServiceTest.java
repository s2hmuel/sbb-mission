package com.ll.sbbmission.user.service;

import com.ll.sbbmission.user.entity.SiteUser;
import com.ll.sbbmission.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class UserSecurityServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserSecurityService userSecurityService;

    @Test
    void testLoadUserByUsername() {
            // Arrange
        String username = "testUser";
        String password = "testPassword";
        SiteUser siteUser = new SiteUser();
        siteUser.setUsername(username);
        siteUser.setPassword(password);

        when(userRepository.findByusername(username)).thenReturn(Optional.of(siteUser));

        // Act
        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
    }

    @Test
    void testLoadUserByUsernameException() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";

        when(userRepository.findByusername(nonExistingUsername)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userSecurityService.loadUserByUsername(nonExistingUsername);
        });
    }
}
