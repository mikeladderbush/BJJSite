package com.BJJ.BJJSite.Repositories;

import com.BJJ.BJJSite.Classes.PaymentOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PaymentOptionRepositoryTest {

    @Autowired
    private PaymentOptionRepository paymentOptionRepository;

    private PaymentOption paymentOption;

    @BeforeEach
    void setUp() {
        paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/23")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        // Save the PaymentOption in the in-memory database
        paymentOptionRepository.save(paymentOption);
    }

    @Test
    void testFindByName() {
        Optional<PaymentOption> foundPaymentOption = paymentOptionRepository.findByName("John Doe");

        assertTrue(foundPaymentOption.isPresent());
        assertEquals("John Doe", foundPaymentOption.get().getName());
        assertEquals("4111111111111111", foundPaymentOption.get().getCardNumber());
    }

    @Test
    void testFindById() {
        Optional<PaymentOption> foundPaymentOption = paymentOptionRepository.findById(paymentOption.getPaymentOptionId());

        assertTrue(foundPaymentOption.isPresent());
        assertEquals(paymentOption.getPaymentOptionId(), foundPaymentOption.get().getPaymentOptionId());
    }

    @Test
    void testFindByCardNumber() {
        Optional<PaymentOption> foundPaymentOption = paymentOptionRepository.findByCardNumber("4111111111111111");

        assertTrue(foundPaymentOption.isPresent());
        assertEquals("4111111111111111", foundPaymentOption.get().getCardNumber());
    }

    @Test
    void testFindByName_NotFound() {
        Optional<PaymentOption> foundPaymentOption = paymentOptionRepository.findByName("Nonexistent Name");

        assertFalse(foundPaymentOption.isPresent());
    }

    @Test
    void testFindByCardNumber_NotFound() {
        Optional<PaymentOption> foundPaymentOption = paymentOptionRepository.findByCardNumber("0000000000000000");

        assertFalse(foundPaymentOption.isPresent());
    }
}
