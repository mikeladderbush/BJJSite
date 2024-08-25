package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Interfaces.ServiceInterface;

/**
 * Service class for handling operations related to Administrators.
 * 
 * This class implements the ServiceInterface and provides functionality specific to administrators, such as generating unique IDs.
 */
public class AdministratorService implements ServiceInterface {

    /**
     * Generates a unique ID for an Administrator.
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
