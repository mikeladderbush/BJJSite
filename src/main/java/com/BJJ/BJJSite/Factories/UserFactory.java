package com.BJJ.BJJSite.Factories;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BJJ.BJJSite.Classes.Administrator;
import com.BJJ.BJJSite.Classes.Employee;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Enums.Permissions;
import com.BJJ.BJJSite.Services.UserService;

@Component
public class UserFactory {

    @Autowired
    private UserService userService;

    // Method to create a User with default values
    public User createUser() {
        Permissions permissions = Permissions.USER_ACCESS;
        User user = new User.UserBuilder<>(permissions).buildUser();
        return userService.createUser(user);
    }

    public User createUser(Consumer<User.UserBuilder<?>> consumer) {
        Permissions permissions = Permissions.USER_ACCESS;
        User.UserBuilder<?> builder = new User.UserBuilder<>(permissions);
        consumer.accept(builder);
        User user = builder.buildUser();
        return userService.createUser(user);
    }

    public Employee createEmployee() {
        Permissions permissions = Permissions.FULL_CONTROL;
        Employee employee = new Employee.EmployeeBuilder<>(permissions).buildUser();
        return userService.createUser(employee);
    }

    public Employee createEmployee(Consumer<Employee.EmployeeBuilder<?>> consumer) {
        Permissions permissions = Permissions.EMPLOYEE_ACCESS;
        Employee.EmployeeBuilder<?> builder = new Employee.EmployeeBuilder<>(permissions);
        consumer.accept(builder);
        Employee employee = builder.buildUser();
        return userService.createUser(employee);
    }

    public Administrator createAdministrator() {
        Permissions permissions = Permissions.FULL_CONTROL;
        Administrator administrator = new Administrator.AdministratorBuilder(permissions).buildUser();
        return userService.createUser(administrator);
    }

    public Administrator createAdministrator(Consumer<Administrator.AdministratorBuilder> consumer) {
        Permissions permissions = Permissions.FULL_CONTROL;
        Administrator.AdministratorBuilder builder = new Administrator.AdministratorBuilder(permissions);
        consumer.accept(builder);
        Administrator administrator = builder.buildUser();
        return userService.createUser(administrator);
    }
}
