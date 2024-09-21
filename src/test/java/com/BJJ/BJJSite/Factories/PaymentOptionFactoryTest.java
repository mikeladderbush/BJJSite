package com.BJJ.BJJSite.Factories;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Services.PaymentOptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentOptionFactoryTest {

    @Mock
    private PaymentOptionService paymentOptionService;

    @InjectMocks
    private PaymentOptionFactory paymentOptionFactory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePaymentOption_Success() throws PaymentOptionAlreadyExistsException {
        PaymentOption mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/23")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        when(paymentOptionService.createPaymentOption(any(PaymentOption.class))).thenReturn(mockPaymentOption);

        Optional<PaymentOption> result = paymentOptionFactory.createPaymentOption();

        assertTrue(result.isPresent());
        assertEquals("4111111111111111", result.get().getCardNumber());
        verify(paymentOptionService, times(1)).createPaymentOption(any(PaymentOption.class));
    }

    @Test
    void testCreatePaymentOption_PaymentOptionAlreadyExists() throws PaymentOptionAlreadyExistsException {
        when(paymentOptionService.createPaymentOption(any(PaymentOption.class)))
                .thenThrow(new PaymentOptionAlreadyExistsException("Payment option already exists"));

        Optional<PaymentOption> result = paymentOptionFactory.createPaymentOption();

        assertFalse(result.isPresent());
        verify(paymentOptionService, times(1)).createPaymentOption(any(PaymentOption.class));
    }

    @Test
    void testCreatePaymentOptionWithCustomValues_Success() throws PaymentOptionAlreadyExistsException {
        PaymentOption mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Jane Doe")
                .cardNumber("4222222222222222")
                .expirationDate("01/24")
                .confirmationCode("456")
                .cardType("MasterCard")
                .billingAddress("456 Elm St")
                .billingZipCode("54321")
                .buildPaymentOption();

        when(paymentOptionService.createPaymentOption(any(PaymentOption.class))).thenReturn(mockPaymentOption);

        PaymentOption result = paymentOptionFactory.createPaymentOption(builder -> builder
                .name("Jane Doe")
                .cardNumber("4222222222222222")
                .expirationDate("01/24")
                .confirmationCode("456")
                .cardType("MasterCard")
                .billingAddress("456 Elm St")
                .billingZipCode("54321"));

        assertNotNull(result);
        assertEquals("4222222222222222", result.getCardNumber());
        assertEquals("MasterCard", result.getCardType());
        verify(paymentOptionService, times(1)).createPaymentOption(any(PaymentOption.class));
    }
}
