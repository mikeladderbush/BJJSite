package com.BJJ.BJJSite.Factories;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.BJJ.BJJSite.Classes.Administrator;
import com.BJJ.BJJSite.Classes.Employee;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Services.UserService;

//TODO - Current tests returning null pointer exception

class UserFactoryTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserFactory userFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
     * @Test
     * void createUser_WithDefaultValues_Success() throws UserAlreadyExistsException
     * {
     * // Arrange
     * User user = new User.UserBuilder<>().buildUser();
     * when(userService.createUser(user)).thenReturn(user);
     * 
     * // Act
     * Optional<User> createdUser = userFactory.createUser();
     * 
     * // Assert
     * assertTrue(createdUser.isPresent());
     * assertEquals(user, createdUser.get());
     * verify(userService).createUser(user);
     * }
     */

    /*
     * @Test
     * void createUser_WithCustomValues_Success() throws UserAlreadyExistsException
     * {
     * // Arrange
     * User user = new User.UserBuilder<>()
     * .username("testuser")
     * .email("test@example.com")
     * .buildUser();
     * when(userService.createUser(user)).thenReturn(user);
     * 
     * // Act
     * Optional<User> createdUser = userFactory.createUser(builder -> {
     * builder.username("testuser")
     * .email("test@example.com");
     * });
     * 
     * // Assert
     * assertTrue(createdUser.isPresent());
     * assertEquals("testuser", createdUser.get().getUsername());
     * assertEquals("test@example.com", createdUser.get().getEmail());
     * verify(userService).createUser(user);
     * }
     */

    /*
     * @Test
     * void createUser_UserAlreadyExists_ReturnsEmpty() throws
     * UserAlreadyExistsException {
     * // Arrange
     * User user = new User.UserBuilder<>().buildUser();
     * when(userService.createUser(user)).thenThrow(new
     * UserAlreadyExistsException("User already exists"));
     * 
     * // Act
     * Optional<User> createdUser = userFactory.createUser();
     * 
     * // Assert
     * assertTrue(createdUser.isEmpty());
     * verify(userService).createUser(user);
     * }
     */

    /*
     * @Test
     * void createEmployee_WithDefaultValues_Success() throws
     * UserAlreadyExistsException {
     * // Arrange
     * Employee employee = new Employee.EmployeeBuilder<>().buildUser();
     * when(userService.createUser(employee)).thenReturn(employee);
     * 
     * // Act
     * Optional<Employee> createdEmployee = userFactory.createEmployee();
     * 
     * // Assert
     * assertTrue(createdEmployee.isPresent());
     * assertEquals(employee, createdEmployee.get());
     * verify(userService).createUser(employee);
     * }
     */

    /*
     * @Test
     * void createAdministrator_WithCustomValues_Success() throws
     * UserAlreadyExistsException {
     * // Arrange
     * Administrator admin = new Administrator.AdministratorBuilder()
     * .adminLevel("SUPER_ADMIN")
     * .buildUser();
     * when(userService.createUser(admin)).thenReturn(admin);
     * 
     * // Act
     * Optional<Administrator> createdAdmin =
     * userFactory.createAdministrator(builder -> {
     * builder.adminLevel("SUPER_ADMIN");
     * });
     * 
     * // Assert
     * assertTrue(createdAdmin.isPresent());
     * assertEquals("SUPER_ADMIN", createdAdmin.get().getAdminLevel());
     * verify(userService).createUser(admin);
     * }
     */

    /*
     * @Test
     * void createAdministrator_AdminAlreadyExists_ReturnsEmpty() throws
     * UserAlreadyExistsException {
     * // Arrange
     * Administrator admin = new Administrator.AdministratorBuilder().buildUser();
     * when(userService.createUser(admin)).thenThrow(new
     * UserAlreadyExistsException("Administrator already exists"));
     * 
     * // Act
     * Optional<Administrator> createdAdmin = userFactory.createAdministrator();
     * 
     * // Assert
     * assertTrue(createdAdmin.isEmpty());
     * verify(userService).createUser(admin);
     * }
     */
}
