package com.BJJ.BJJSite.Repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import com.BJJ.BJJSite.Classes.PaymentOption;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PaymentOptionRepositoryTest {

    @Autowired
    private PaymentOptionRepository paymentOptionRepository;

    private PaymentOption paymentOption;

    @BeforeEach
    void setUp() {
        // Prepare a PaymentOption entity for testing
        paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Test Option")
                .cardNumber("1234567890123456")
                .expirationDate("12/23")
                .confirmationCode("123")
                .cardType("VISA")
                .billingAddress("123 Test St")
                .billingZipCode("12345")
                .buildPaymentOption();

        // Save the entity to the repository
        paymentOptionRepository.save(paymentOption);
    }

    /*
    @Test
    void testFindByName() {
        // Act
        Optional<PaymentOption> foundOption = paymentOptionRepository.findByName("Test Option");

        // Assert
        assertTrue(foundOption.isPresent(), "PaymentOption should be found by name");
        assertEquals(paymentOption.getName(), foundOption.get().getName(), "PaymentOption name should match");
    }
    */

    /*
    @Test
    void testFindById() {
        // Act
        Optional<PaymentOption> foundOption = paymentOptionRepository.findById(paymentOption.getPaymentOptionId());

        // Assert
        assertTrue(foundOption.isPresent(), "PaymentOption should be found by ID");
        assertEquals(paymentOption.getPaymentOptionId(), foundOption.get().getPaymentOptionId(), "PaymentOption ID should match");
    }
    */

    /*
    @Test
    void testFindByCardNumber() {
        // Act
        Optional<PaymentOption> foundOption = paymentOptionRepository.findByCardNumber("1234567890123456");

        // Assert
        assertTrue(foundOption.isPresent(), "PaymentOption should be found by card number");
        assertEquals(paymentOption.getCardNumber(), foundOption.get().getCardNumber(), "PaymentOption card number should match");
    }
    */
}
