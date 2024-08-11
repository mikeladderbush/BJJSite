package com.BJJ.BJJSite.Classes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment_options")
public class PaymentOption {

    public PaymentOption() {
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long paymentOptionId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String confirmationCode;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private String billingAddress;

    @Column(nullable = false)
    private String billingZipCode;

    public void setUser(User user) {
        this.user = user;
    }

    protected PaymentOption(PaymentOptionBuilder PaymentOptionBuilder) {
        name = PaymentOptionBuilder.name;
        cardNumber = PaymentOptionBuilder.cardNumber;
        expirationDate = PaymentOptionBuilder.expirationDate;
        confirmationCode = PaymentOptionBuilder.confirmationCode;
        cardType = PaymentOptionBuilder.cardType;
        billingAddress = PaymentOptionBuilder.billingAddress;
        billingZipCode = PaymentOptionBuilder.billingZipCode;
    }

    public static class PaymentOptionBuilder {

        private String name = "NO_PAYMENT_NAME_ON_FILE";
        private String cardNumber = "NO_CARD_NUMBER_ON_FILE";
        private String expirationDate = "NO_EXPIRATION_DATE_ON_FILE";
        private String confirmationCode = "NO_CONFIRMATION_CODE_ON_FILE";
        private String cardType = "NO_CARD_TYPE_ON_FILE";
        private String billingAddress = "NO_BILLING_ADDRESS_ON_FILE";
        private String billingZipCode = "NO_BILLING_ZIP_CODE_ON_FILE";

        public PaymentOptionBuilder() {
        }

        public PaymentOptionBuilder name(String value) {
            this.name = value;
            return self();
        }

        public PaymentOptionBuilder cardNumber(String value) {
            this.cardNumber = value;
            return self();

        }

        public PaymentOptionBuilder expirationDate(String value) {
            this.expirationDate = value;
            return self();
        }

        public PaymentOptionBuilder confirmationCode(String value) {
            this.confirmationCode = value;
            return self();
        }

        public PaymentOptionBuilder cardType(String value) {
            this.cardType = value;
            return self();
        }

        public PaymentOptionBuilder billingAddress(String value) {
            this.billingAddress = value;
            return self();
        }

        public PaymentOptionBuilder billingZipCode(String value) {
            this.billingZipCode = value;
            return self();
        }

        protected PaymentOptionBuilder self() {
            return this;
        }

        public PaymentOption buildPaymentOption() {
            return new PaymentOption(this);
        }

    }

    /**
     * Retrieves the ID of the contact.
     * 
     * @return The ID of the contact.
     */
    public Long getPaymentOptionId() {
        return paymentOptionId;
    }

    /**
     * For testing purpose only.
     * 
     * @param id
     */
    public void setId(Long paymentOptionId) {
        this.paymentOptionId = paymentOptionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingZipCode(String billingZipCode) {
        this.billingZipCode = billingZipCode;
    }

    public String getBillingZipCode() {
        return billingZipCode;
    }

}
