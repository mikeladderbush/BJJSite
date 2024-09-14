package com.BJJ.BJJSite.Classes;

import com.BJJ.BJJSite.Enums.PayBasis;

/**
 * Represents an Employee.
 * 
 * The Employee class extends the User class, adding attributes specific to an employee,
 * such as social security number, position, pay rate, pay basis, and base earnings.
 */
public class Employee extends User {

    private final String socialSecurityNumber;
    private final String position;
    private final double payrate;
    private final PayBasis paybasis;
    private final double baseEarnings;

    /**
     * Protected constructor to be called by the EmployeeBuilder.
     * 
     * @param builder The builder used to construct the Employee instance.
     */
    protected Employee(EmployeeBuilder<?> builder) {
        super(builder);
        this.socialSecurityNumber = builder.socialSecurityNumber;
        this.position = builder.position;
        this.payrate = builder.payrate;
        this.paybasis = builder.paybasis;
        this.baseEarnings = builder.baseEarnings;
    }

    /**
     * Builder class for creating an Employee instance.
     * 
     * This builder pattern allows for flexible and customizable creation of Employee objects,
     * including setting various employee-specific attributes.
     */
    public static class EmployeeBuilder<T extends EmployeeBuilder<T>> extends UserBuilder<T> {

        private String socialSecurityNumber = "000-00-0000";
        private String position = "DEFAULT_HIRE";
        private double payrate = 0.0;
        private PayBasis paybasis = PayBasis.VOLUNTEER;
        private double baseEarnings = 0.0;

        /**
         * Default constructor for EmployeeBuilder.
         */
        public EmployeeBuilder() {
        }

        /**
         * Sets the social security number for the Employee.
         * 
         * @param value The social security number.
         * @return The current instance of EmployeeBuilder.
         */
        public T socialSecurityNumber(String value) {
            this.socialSecurityNumber = value;
            return self();
        }

        /**
         * Sets the position for the Employee.
         * 
         * @param value The position title.
         * @return The current instance of EmployeeBuilder.
         */
        public T position(String value) {
            this.position = value;
            return self();
        }

        /**
         * Sets the pay rate for the Employee.
         * 
         * @param value The pay rate.
         * @return The current instance of EmployeeBuilder.
         */
        public T payrate(double value) {
            this.payrate = value;
            return self();
        }

        /**
         * Sets the pay basis for the Employee.
         * 
         * @param value The pay basis (e.g., HOURLY, SALARY).
         * @return The current instance of EmployeeBuilder.
         */
        public T paybasis(PayBasis value) {
            this.paybasis = value;
            return self();
        }

        /**
         * Sets the base earnings for the Employee.
         * 
         * @param value The base earnings.
         * @return The current instance of EmployeeBuilder.
         */
        public T baseEarnings(double value) {
            this.baseEarnings = value;
            return self();
        }

        /**
         * Ensures that the builder returns the correct type for method chaining.
         * 
         * @return The current instance of EmployeeBuilder.
         */
        @SuppressWarnings("unchecked")
        @Override
        protected T self() {
            return (T) this;
        }

        /**
         * Builds and returns an Employee instance.
         * 
         * @return A new Employee object.
         */
        @Override
        public Employee buildUser() {
            return new Employee(this);
        }
    }

    /**
     * Retrieves the user ID of the Employee.
     * 
     * @return The user ID.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Retrieves the social security number of the Employee.
     * 
     * @return The social security number.
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Retrieves the position of the Employee.
     * 
     * @return The position title.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Retrieves the pay rate of the Employee.
     * 
     * @return The pay rate.
     */
    public double getPayrate() {
        return payrate;
    }

    /**
     * Retrieves the pay basis of the Employee.
     * 
     * @return The pay basis (e.g., HOURLY, SALARY).
     */
    public PayBasis getPaybasis() {
        return paybasis;
    }

    /**
     * Retrieves the base earnings of the Employee.
     * 
     * @return The base earnings.
     */
    public double getBaseEarnings() {
        return baseEarnings;
    }
}
