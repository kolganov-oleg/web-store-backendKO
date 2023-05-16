package ru.skypro.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {

    private UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken("test@example.com", null));
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testSetPassword_Success() {
        User currentUser = new User();
        currentUser.setPassword("oldPassword");

        when(userRepository.save(any(User.class))).thenReturn(currentUser);
        when(userRepository.findByEmail(anyString())).thenReturn(currentUser);

        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword("oldPassword");
        newPasswordDto.setNewPassword("newPassword");

        boolean result = userService.setPassword(newPasswordDto);

        assertTrue(result);
        assertEquals("newPassword", currentUser.getPassword());
    }

    @Test
    public void testSetPassword_Failure() {
        User currentUser = new User();
        currentUser.setPassword("oldPassword");

        when(userRepository.save(any(User.class))).thenReturn(currentUser);
        when(userRepository.findByEmail(anyString())).thenReturn(currentUser);

        NewPasswordDto newPasswordDto = new NewPasswordDto();
        newPasswordDto.setCurrentPassword("wrongPassword");
        newPasswordDto.setNewPassword("newPassword");

        boolean result = userService.setPassword(newPasswordDto);

        assertFalse(result);
        assertEquals("oldPassword", currentUser.getPassword());
    }

    @Test
    public void testGetMe() {
        User currentUser = new User();
        currentUser.setEmail("test@example.com");
        currentUser.setFirstName("John");
        currentUser.setLastName("Doe");
        currentUser.setPhone("1234567890");

        when(userRepository.findByEmail("test@example.com")).thenReturn(currentUser);

        UserDto userDto = userService.getMe();

        assertNotNull(userDto);
        assertEquals("John", userDto.getFirstName());
        assertEquals("Doe", userDto.getLastName());
        assertEquals("1234567890", userDto.getPhone());
    }

    @Test
    void updateUser() {
    }

    @Test
    void updateImage() {
    }
}