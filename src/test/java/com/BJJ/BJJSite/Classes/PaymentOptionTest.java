package com.BJJ.BJJSite.Classes;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentOptionTest {

    private PaymentOption paymentOption;

    @BeforeEach
    void setUp() {
        paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/24")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();
    }

    @Test
    void testGetName() {
        assertEquals("John Doe", paymentOption.getName());
    }

    @Test
    void testSetName() {
        paymentOption.setName("Jane Doe");
        assertEquals("Jane Doe", paymentOption.getName());
    }

    @Test
    void testGetCardNumber() {
        assertEquals("4111111111111111", paymentOption.getCardNumber());
    }

    @Test
    void testSetCardNumber() {
        paymentOption.setCardNumber("4222222222222222");
        assertEquals("4222222222222222", paymentOption.getCardNumber());
    }

    @Test
    void testGetExpirationDate() {
        assertEquals("12/24", paymentOption.getExpirationDate());
    }

    @Test
    void testSetExpirationDate() {
        paymentOption.setExpirationDate("01/25");
        assertEquals("01/25", paymentOption.getExpirationDate());
    }

    @Test
    void testGetConfirmationCode() {
        assertEquals("123", paymentOption.getConfirmationCode());
    }

    @Test
    void testSetConfirmationCode() {
        paymentOption.setConfirmationCode("456");
        assertEquals("456", paymentOption.getConfirmationCode());
    }

    @Test
    void testGetCardType() {
        assertEquals("Visa", paymentOption.getCardType());
    }

    @Test
    void testSetCardType() {
        paymentOption.setCardType("MasterCard");
        assertEquals("MasterCard", paymentOption.getCardType());
    }

    @Test
    void testGetBillingAddress() {
        assertEquals("123 Main St", paymentOption.getBillingAddress());
    }

    @Test
    void testSetBillingAddress() {
        paymentOption.setBillingAddress("456 Elm St");
        assertEquals("456 Elm St", paymentOption.getBillingAddress());
    }

    @Test
    void testGetBillingZipCode() {
        assertEquals("12345", paymentOption.getBillingZipCode());
    }

    @Test
    void testSetBillingZipCode() {
        paymentOption.setBillingZipCode("54321");
        assertEquals("54321", paymentOption.getBillingZipCode());
    }

    @Test
    void testBuilderPattern() {
        PaymentOption newPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Alice Smith")
                .cardNumber("4333333333333333")
                .expirationDate("11/23")
                .confirmationCode("789")
                .cardType("MasterCard")
                .billingAddress("789 Oak St")
                .billingZipCode("98765")
                .buildPaymentOption();

        assertEquals("Alice Smith", newPaymentOption.getName());
        assertEquals("4333333333333333", newPaymentOption.getCardNumber());
        assertEquals("11/23", newPaymentOption.getExpirationDate());
        assertEquals("789", newPaymentOption.getConfirmationCode());
        assertEquals("MasterCard", newPaymentOption.getCardType());
        assertEquals("789 Oak St", newPaymentOption.getBillingAddress());
        assertEquals("98765", newPaymentOption.getBillingZipCode());
    }
}
