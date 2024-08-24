package com.BJJ.BJJSite.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a Payment Option associated with a User.
 * 
 * This entity maps to the "payment_options" table in the database and includes
 * details such as card information and billing address. Each PaymentOption is
 * linked to a specific User.
 */
@Entity
@Table(name = "payment_options")
public class PaymentOption {

    /**
     * Default constructor required by JPA.
     */
    public PaymentOption() {
    }

    /**
     * The User associated with this PaymentOption.
     * 
     * This establishes a many-to-one relationship between PaymentOption and User.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The unique identifier for this PaymentOption.
     * 
     * This is the primary key and is auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long paymentOptionId;

    /**
     * The name associated with the payment method.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The card number for the payment method.
     */
    @Column(nullable = false)
    private String cardNumber;

    /**
     * The expiration date of the card in the format MM/YY.
     */
    @Column(nullable = false)
    private String expirationDate;

    /**
     * The confirmation code for the payment method.
     */
    @Column(nullable = false)
    private String confirmationCode;

    /**
     * The type of card (e.g., Visa, MasterCard).
     */
    @Column(nullable = false)
    private String cardType;

    /**
     * The billing address associated with the payment method.
     */
    @Column(nullable = false)
    private String billingAddress;

    /**
     * The billing ZIP code associated with the payment method.
     */
    @Column(nullable = false)
    private String billingZipCode;

    /**
     * Sets the User associated with this PaymentOption.
     * 
     * @param user The User to associate with this PaymentOption.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Protected constructor to be used by the PaymentOptionBuilder.
     * 
     * @param builder The builder used to construct the PaymentOption instance.
     */
    protected PaymentOption(PaymentOptionBuilder builder) {
        this.name = builder.name;
        this.cardNumber = builder.cardNumber;
        this.expirationDate = builder.expirationDate;
        this.confirmationCode = builder.confirmationCode;
        this.cardType = builder.cardType;
        this.billingAddress = builder.billingAddress;
        this.billingZipCode = builder.billingZipCode;
    }

    /**
     * Builder class for creating instances of PaymentOption.
     * 
     * This builder pattern facilitates the construction of PaymentOption objects
     * with customizable attributes.
     */
    public static class PaymentOptionBuilder {

        private String name = "NO_PAYMENT_NAME_ON_FILE";
        private String cardNumber = "NO_CARD_NUMBER_ON_FILE";
        private String expirationDate = "NO_EXPIRATION_DATE_ON_FILE";
        private String confirmationCode = "NO_CONFIRMATION_CODE_ON_FILE";
        private String cardType = "NO_CARD_TYPE_ON_FILE";
        private String billingAddress = "NO_BILLING_ADDRESS_ON_FILE";
        private String billingZipCode = "NO_BILLING_ZIP_CODE_ON_FILE";

        /**
         * Default constructor for PaymentOptionBuilder.
         */
        public PaymentOptionBuilder() {
        }

        /**
         * Sets the name for the PaymentOption.
         * 
         * @param value The payment option name.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder name(String value) {
            this.name = value;
            return self();
        }

        /**
         * Sets the card number for the PaymentOption.
         * 
         * @param value The card number.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder cardNumber(String value) {
            this.cardNumber = value;
            return self();
        }

        /**
         * Sets the expiration date for the PaymentOption.
         * 
         * @param value The expiration date.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder expirationDate(String value) {
            this.expirationDate = value;
            return self();
        }

        /**
         * Sets the confirmation code for the PaymentOption.
         * 
         * @param value The confirmation code.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder confirmationCode(String value) {
            this.confirmationCode = value;
            return self();
        }

        /**
         * Sets the card type for the PaymentOption.
         * 
         * @param value The card type.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder cardType(String value) {
            this.cardType = value;
            return self();
        }

        /**
         * Sets the billing address for the PaymentOption.
         * 
         * @param value The billing address.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder billingAddress(String value) {
            this.billingAddress = value;
            return self();
        }

        /**
         * Sets the billing ZIP code for the PaymentOption.
         * 
         * @param value The billing ZIP code.
         * @return The current instance of PaymentOptionBuilder.
         */
        public PaymentOptionBuilder billingZipCode(String value) {
            this.billingZipCode = value;
            return self();
        }

        /**
         * Returns the current instance of PaymentOptionBuilder.
         * 
         * This method is useful for method chaining.
         * 
         * @return The current instance of PaymentOptionBuilder.
         */
        protected PaymentOptionBuilder self() {
            return this;
        }

        /**
         * Builds and returns a PaymentOption instance.
         * 
         * @return A new PaymentOption object with the specified attributes.
         */
        public PaymentOption buildPaymentOption() {
            return new PaymentOption(this);
        }

    }

    /**
     * Retrieves the unique identifier of the PaymentOption.
     * 
     * @return The payment option ID.
     */
    public Long getPaymentOptionId() {
        return paymentOptionId;
    }

    /**
     * Sets the unique identifier of the PaymentOption.
     * 
     * This method is primarily intended for testing purposes.
     * 
     * @param paymentOptionId The ID to set.
     */
    public void setId(Long paymentOptionId) {
        this.paymentOptionId = paymentOptionId;
    }

    /**
     * Sets the name of the PaymentOption.
     * 
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the PaymentOption.
     * 
     * @return The payment option name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the card number associated with the PaymentOption.
     * 
     * @param cardNumber The card number to set.
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Retrieves the card number associated with the PaymentOption.
     * 
     * @return The card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets the expiration date of the PaymentOption.
     * 
     * @param expirationDate The expiration date to set.
     */
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Retrieves the expiration date of the PaymentOption.
     * 
     * @return The expiration date.
     */
    public String getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the confirmation code of the PaymentOption.
     * 
     * @param confirmationCode The confirmation code to set.
     */
    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    /**
     * Retrieves the confirmation code associated with the PaymentOption.
     * 
     * @return The confirmation code.
     */
    public String getConfirmationCode() {
        return confirmationCode;
    }

    /**
     * Sets the card type of the PaymentOption.
     * 
     * @param cardType The card type to set.
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    /**
     * Retrieves the card type of the PaymentOption.
     * 
     * @return The card type.
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * Sets the billing address associated with the PaymentOption.
     * 
     * @param billingAddress The billing address to set.
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * Retrieves the billing address associated with the PaymentOption.
     * 
     * @return The billing address.
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the billing ZIP code associated with the PaymentOption.
     * 
     * @param billingZipCode The billing ZIP code to set.
     */
    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    /**
     * Retrieves the billing ZIP code associated with the PaymentOption.
     * 
     * @return The billing ZIP code.
     */
    public String getBillingZipCode() {
        return billingZipCode;
    }

}
