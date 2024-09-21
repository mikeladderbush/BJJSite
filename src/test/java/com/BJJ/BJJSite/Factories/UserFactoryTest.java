package com.BJJ.BJJSite.Factories;

import com.BJJ.BJJSite.Classes.Administrator;
import com.BJJ.BJJSite.Classes.Employee;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserFactoryTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() throws UserAlreadyExistsException {
        User mockUser = new User.UserBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .buildUser();

        when(userService.createUser(any(User.class))).thenReturn(mockUser);

        Optional<User> result = userFactory.createUser();

        assertTrue(result.isPresent());
        assertEquals("johndoe", result.get().getUsername());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUser_UserAlreadyExists() throws UserAlreadyExistsException {
        when(userService.createUser(any(User.class))).thenThrow(new UserAlreadyExistsException("User already exists"));

        Optional<User> result = userFactory.createUser();

        assertFalse(result.isPresent());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateUserWithCustomValues_Success() throws UserAlreadyExistsException {
        User mockUser = new User.UserBuilder<>()
                .firstname("Jane")
                .lastname("Doe")
                .username("janedoe")
                .email("jane.doe@example.com")
                .buildUser();

        when(userService.createUser(any(User.class))).thenReturn(mockUser);

        Optional<User> result = userFactory.createUser(builder -> builder.firstname("Jane").lastname("Doe"));

        assertTrue(result.isPresent());
        assertEquals("janedoe", result.get().getUsername());
        assertEquals("Jane", result.get().getFirstname());
        verify(userService, times(1)).createUser(any(User.class));
    }

    @Test
    void testCreateEmployee_Success() throws UserAlreadyExistsException {
        Employee mockEmployee = new Employee.EmployeeBuilder<>()
                .firstname("John")
                .lastname("Doe")
                .username("johndoe")
                .email("john.doe@example.com")
                .buildUser();

        when(userService.createUser(any(Employee.class))).thenReturn(mockEmployee);

        Optional<Employee> result = userFactory.createEmployee();

        assertTrue(result.isPresent());
        assertEquals("johndoe", result.get().getUsername());
        verify(userService, times(1)).createUser(any(Employee.class));
    }

    @Test
    void testCreateAdministrator_Success() throws UserAlreadyExistsException {
        Administrator mockAdmin = new Administrator.AdministratorBuilder()
                .firstname("John")
                .lastname("Doe")
                .username("adminjohndoe")
                .email("john.doe@example.com")
                .adminLevel("SUPER_ADMIN")
                .buildUser();

        when(userService.createUser(any(Administrator.class))).thenReturn(mockAdmin);

        Optional<Administrator> result = userFactory.createAdministrator();

        assertTrue(result.isPresent());
        assertEquals("adminjohndoe", result.get().getUsername());
        assertEquals("SUPER_ADMIN", result.get().getAdminLevel());
        verify(userService, times(1)).createUser(any(Administrator.class));
    }

    @Test
    void testCreateAdministratorWithCustomValues_Success() throws UserAlreadyExistsException {
        Administrator mockAdmin = new Administrator.AdministratorBuilder()
                .firstname("Jane")
                .lastname("Doe")
                .username("adminjanedoe")
                .email("jane.doe@example.com")
                .adminLevel("HR_ADMIN")
                .buildUser();

        when(userService.createUser(any(Administrator.class))).thenReturn(mockAdmin);

        Optional<Administrator> result = userFactory.createAdministrator(builder -> builder
                .firstname("Jane")
                .lastname("Doe")
                .adminLevel("HR_ADMIN"));

        assertTrue(result.isPresent());
        assertEquals("adminjanedoe", result.get().getUsername());
        assertEquals("HR_ADMIN", result.get().getAdminLevel());
        verify(userService, times(1)).createUser(any(Administrator.class));
    }
}
