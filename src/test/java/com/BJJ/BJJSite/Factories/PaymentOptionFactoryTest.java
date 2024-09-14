package com.BJJ.BJJSite.Factories;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Services.PaymentOptionService;

//TODO nullpointer exceptions in some methods

@SpringBootTest
class PaymentOptionFactoryTest {

    @Mock
    private PaymentOptionService paymentOptionService;

    @InjectMocks
    private PaymentOptionFactory paymentOptionFactory;

    /*
    @Test
    void testCreatePaymentOptionSuccess() throws PaymentOptionAlreadyExistsException {
        // Arrange
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("1234-5678-9012-3456")
                .expirationDate("12/24")
                .confirmationCode("123")
                .cardType("VISA")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        when(paymentOptionService.createPaymentOption(paymentOption)).thenReturn(paymentOption);

        // Act
        Optional<PaymentOption> result = paymentOptionFactory.createPaymentOption();

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }
    */

    /*
    @Test
    void testCreatePaymentOptionAlreadyExists() throws PaymentOptionAlreadyExistsException {
        // Arrange
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("1234-5678-9012-3456")
                .expirationDate("12/24")
                .confirmationCode("123")
                .cardType("VISA")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        when(paymentOptionService.createPaymentOption(paymentOption))
                .thenThrow(new PaymentOptionAlreadyExistsException("Payment option already exists"));

        // Act
        Optional<PaymentOption> result = paymentOptionFactory.createPaymentOption();

        // Assert
        assertFalse(result.isPresent());
        verify(paymentOptionService, never()).createPaymentOption(paymentOption);
    }
    */

    @Test
    void testCreateCustomPaymentOption() {
        // Act
        PaymentOption paymentOption = paymentOptionFactory.createPaymentOption(builder -> {
            builder.name("Jane Doe")
                    .cardNumber("9876-5432-1098-7654")
                    .expirationDate("11/23")
                    .confirmationCode("456")
                    .cardType("MasterCard")
                    .billingAddress("456 Elm St")
                    .billingZipCode("54321");
        });

        // Assert
        assertEquals("Jane Doe", paymentOption.getName());
        assertEquals("9876-5432-1098-7654", paymentOption.getCardNumber());
        assertEquals("11/23", paymentOption.getExpirationDate());
        assertEquals("456", paymentOption.getConfirmationCode());
        assertEquals("MasterCard", paymentOption.getCardType());
        assertEquals("456 Elm St", paymentOption.getBillingAddress());
        assertEquals("54321", paymentOption.getBillingZipCode());
    }
}
