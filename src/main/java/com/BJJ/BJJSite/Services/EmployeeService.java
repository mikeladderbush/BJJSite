package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Interfaces.ServiceInterface;

/**
 * Service class for handling operations related to Employees.
 * 
 * This class implements the ServiceInterface and provides functionality specific to employees, such as generating unique IDs.
 */
public class EmployeeService implements ServiceInterface {

    /**
     * Generates a unique ID for an Employee.
     * 
     * This method overrides the default implementation provided by the ServiceInterface.
     * 
     * @return A unique ID generated using the method from the ServiceInterface.
     */
    @Override
    public Long generateId() {
        return ServiceInterface.super.generateId();
    }

}
