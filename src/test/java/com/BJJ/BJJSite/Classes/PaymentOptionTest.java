package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentOptionTest {

    private PaymentOption.PaymentOptionBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/25")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345");
    }

    @Test
    void testPaymentOptionCreation() {
        PaymentOption paymentOption = builder.buildPaymentOption();

        assertNotNull(paymentOption);
        assertEquals("John Doe", paymentOption.getName());
        assertEquals("4111111111111111", paymentOption.getCardNumber());
        assertEquals("12/25", paymentOption.getExpirationDate());
        assertEquals("123", paymentOption.getConfirmationCode());
        assertEquals("Visa", paymentOption.getCardType());
        assertEquals("123 Main St", paymentOption.getBillingAddress());
        assertEquals("12345", paymentOption.getBillingZipCode());
    }

    @Test
    void testDefaultValues() {
        PaymentOption defaultPaymentOption = new PaymentOption.PaymentOptionBuilder().buildPaymentOption();

        assertNotNull(defaultPaymentOption);
        assertEquals("NO_PAYMENT_NAME_ON_FILE", defaultPaymentOption.getName());
        assertEquals("NO_CARD_NUMBER_ON_FILE", defaultPaymentOption.getCardNumber());
        assertEquals("NO_EXPIRATION_DATE_ON_FILE", defaultPaymentOption.getExpirationDate());
        assertEquals("NO_CONFIRMATION_CODE_ON_FILE", defaultPaymentOption.getConfirmationCode());
        assertEquals("NO_CARD_TYPE_ON_FILE", defaultPaymentOption.getCardType());
        assertEquals("NO_BILLING_ADDRESS_ON_FILE", defaultPaymentOption.getBillingAddress());
        assertEquals("NO_BILLING_ZIP_CODE_ON_FILE", defaultPaymentOption.getBillingZipCode());
    }

    @Test
    void testSetUser() {
        User user = new User.UserBuilder<>()
                .fullName("Jane Doe")
                .email("jane.doe@example.com")
                .buildUser();

        PaymentOption paymentOption = builder.buildPaymentOption();
        paymentOption.setUser(user);

        assertNotNull(paymentOption);
        assertEquals(user, paymentOption.user);
    }
}
