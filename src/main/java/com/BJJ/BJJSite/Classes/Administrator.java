package com.BJJ.BJJSite.Classes;

/**
 * Represents an Administrator, which is a type of Employee.
 * 
 * The Administrator class extends the Employee class, adding an admin level 
 * to distinguish different levels of administrative access.
 */
public class Administrator extends Employee {

    private final String adminLevel;

    /**
     * Private constructor to be called by the AdministratorBuilder.
     * 
     * @param builder The builder used to construct the Administrator instance.
     */
    private Administrator(AdministratorBuilder builder) {
        super(builder);
        this.adminLevel = builder.adminLevel;
    }

    /**
     * Builder class for creating an Administrator instance.
     * 
     * This builder pattern allows for flexible and customizable creation of 
     * Administrator objects, including setting the admin level.
     */
    public static class AdministratorBuilder extends EmployeeBuilder<AdministratorBuilder> {

        private String adminLevel = "DEFAULT ADMIN";

        /**
         * Default constructor for AdministratorBuilder.
         */
        public AdministratorBuilder() {
        }

        /**
         * Sets the admin level for the Administrator.
         * 
         * @param value The desired admin level.
         * @return The current instance of AdministratorBuilder.
         */
        public AdministratorBuilder adminLevel(String value) {
            this.adminLevel = value;
            return self();
        }

        /**
         * Ensures that the builder returns the correct type for method chaining.
         * 
         * @return The current instance of AdministratorBuilder.
         */
        @Override
        protected AdministratorBuilder self() {
            return this;
        }

        /**
         * Builds and returns an Administrator instance.
         * 
         * @return A new Administrator object.
         */
        @Override
        public Administrator buildUser() {
            return new Administrator(this);
        }
    }

    /**
     * Retrieves the user ID of the Administrator.
     * 
     * @return The user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Retrieves the admin level of the Administrator.
     * 
     * @return The admin level.
     */
    public String getAdminLevel() {
        return adminLevel;
    }
}
