package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Interfaces.ServiceInterface;

public class EmployeeService implements ServiceInterface {
    
    public Long generateId() {
        return ServiceInterface.super.generateId();
    }

}
