package com.BJJ.BJJSite.Factories;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BJJ.BJJSite.Classes.Administrator;
import com.BJJ.BJJSite.Classes.Employee;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Services.UserService;

@Component
public class UserFactory {

    @Autowired
    private UserService userService;

    // Method to create a User with default values
    public User createUser() {
        User user = new User.UserBuilder<>().buildUser();
        return userService.createUser(user);
    }

    public User createUser(Consumer<User.UserBuilder<?>> consumer) {
        User.UserBuilder<?> builder = new User.UserBuilder<>();
        consumer.accept(builder);
        User user = builder.buildUser();
        return userService.createUser(user);
    }

    public Employee createEmployee() {
        Employee employee = new Employee.EmployeeBuilder<>().buildUser();
        return userService.createUser(employee);
    }

    public Employee createEmployee(Consumer<Employee.EmployeeBuilder<?>> consumer) {
        Employee.EmployeeBuilder<?> builder = new Employee.EmployeeBuilder<>();
        consumer.accept(builder);
        Employee employee = builder.buildUser();
        return userService.createUser(employee);
    }

    public Administrator createAdministrator() {
        Administrator administrator = new Administrator.AdministratorBuilder().buildUser();
        return userService.createUser(administrator);
    }

    public Administrator createAdministrator(Consumer<Administrator.AdministratorBuilder> consumer) {
        Administrator.AdministratorBuilder builder = new Administrator.AdministratorBuilder();
        consumer.accept(builder);
        Administrator administrator = builder.buildUser();
        return userService.createUser(administrator);
    }
}
