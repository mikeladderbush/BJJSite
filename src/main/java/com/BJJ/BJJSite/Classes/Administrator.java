package com.BJJ.BJJSite.Classes;

/**
 * Represents an Admin.
 */
public class Administrator extends Employee {

    private final String adminLevel;

    private Administrator(AdministratorBuilder builder) {
        super(builder);
        this.adminLevel = builder.adminLevel;
    }

    public static class AdministratorBuilder extends EmployeeBuilder<AdministratorBuilder> {

        private String adminLevel = "DEFAULT ADMIN";

        public AdministratorBuilder() {
        }

        public AdministratorBuilder adminLevel(String value) {
            this.adminLevel = value;
            return self();
        }

        @Override
        protected AdministratorBuilder self() {
            return this;
        }

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
    public Integer getUserId() {
        return userId;
    }

    public String getAdminLevel() {
        return adminLevel;
    }
}
