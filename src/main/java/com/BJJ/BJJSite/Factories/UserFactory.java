package com.BJJ.BJJSite.Factories;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BJJ.BJJSite.Classes.Administrator;
import com.BJJ.BJJSite.Classes.Employee;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.FactoryExceptions.UserAlreadyExistsException;
import com.BJJ.BJJSite.Services.UserService;

/**
 * Factory class for creating instances of User, Employee, and Administrator.
 * 
 * This factory provides methods to create User, Employee, and Administrator objects with default values or with custom configurations.
 */
@Component
public class UserFactory {

    @Autowired
    private UserService userService;

    /**
     * Creates a User with default values.
     * 
     * @return An Optional containing the created User, or an empty Optional if the User already exists.
     */
    public Optional<User> createUser() {
        User user = new User.UserBuilder<>().buildUser();
        try {
            return Optional.of(userService.createUser(user));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates a User with custom values using a Consumer to configure the UserBuilder.
     * 
     * @param consumer A Consumer that applies custom configurations to the UserBuilder.
     * @return An Optional containing the created User, or an empty Optional if the User already exists.
     */
    public Optional<User> createUser(Consumer<User.UserBuilder<?>> consumer) {
        User.UserBuilder<?> builder = new User.UserBuilder<>();
        consumer.accept(builder);
        User user = builder.buildUser();
        try {
            return Optional.of(userService.createUser(user));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates an Employee with default values.
     * 
     * @return An Optional containing the created Employee, or an empty Optional if the Employee already exists.
     */
    public Optional<Employee> createEmployee() {
        Employee employee = new Employee.EmployeeBuilder<>().buildUser();
        try {
            return Optional.of(userService.createUser(employee));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates an Employee with custom values using a Consumer to configure the EmployeeBuilder.
     * 
     * @param consumer A Consumer that applies custom configurations to the EmployeeBuilder.
     * @return An Optional containing the created Employee, or an empty Optional if the Employee already exists.
     */
    public Optional<Employee> createEmployee(Consumer<Employee.EmployeeBuilder<?>> consumer) {
        Employee.EmployeeBuilder<?> builder = new Employee.EmployeeBuilder<>();
        consumer.accept(builder);
        Employee employee = builder.buildUser();
        try {
            return Optional.of(userService.createUser(employee));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates an Administrator with default values.
     * 
     * @return An Optional containing the created Administrator, or an empty Optional if the Administrator already exists.
     */
    public Optional<Administrator> createAdministrator() {
        Administrator administrator = new Administrator.AdministratorBuilder().buildUser();
        try {
            return Optional.of(userService.createUser(administrator));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates an Administrator with custom values using a Consumer to configure the AdministratorBuilder.
     * 
     * @param consumer A Consumer that applies custom configurations to the AdministratorBuilder.
     * @return An Optional containing the created Administrator, or an empty Optional if the Administrator already exists.
     */
    public Optional<Administrator> createAdministrator(Consumer<Administrator.AdministratorBuilder> consumer) {
        Administrator.AdministratorBuilder builder = new Administrator.AdministratorBuilder();
        consumer.accept(builder);
        Administrator administrator = builder.buildUser();
        try {
            return Optional.of(userService.createUser(administrator));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }
}
