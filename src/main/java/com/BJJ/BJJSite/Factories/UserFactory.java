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

@Component
public class UserFactory {

    @Autowired
    private UserService userService;

    // Method to create a User with default values
    public Optional<User> createUser() {
        User user = new User.UserBuilder<>().buildUser();
        try {
            return Optional.of(userService.createUser(user));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

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

    public Optional<Employee> createEmployee() {
        Employee employee = new Employee.EmployeeBuilder<>().buildUser();
        try {
            return Optional.of(userService.createUser(employee));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

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

    public Optional<Administrator> createAdministrator() {
        Administrator administrator = new Administrator.AdministratorBuilder().buildUser();
        try {
            return Optional.of(userService.createUser(administrator));
        } catch (UserAlreadyExistsException e) {
            return Optional.empty();
        }
    }

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
