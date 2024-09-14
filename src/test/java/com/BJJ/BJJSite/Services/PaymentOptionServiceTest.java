package com.BJJ.BJJSite.Services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;

class PaymentOptionServiceTest {

    @Mock
    private PaymentOptionRepository paymentOptionRepository;

    @InjectMocks
    private PaymentOptionService paymentOptionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePaymentOption_NewPaymentOption() throws PaymentOptionAlreadyExistsException {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder().name("New Payment").buildPaymentOption();

        when(paymentOptionRepository.findByName(paymentOption.getName())).thenReturn(Optional.empty());
        when(paymentOptionRepository.save(paymentOption)).thenReturn(paymentOption);

        PaymentOption createdPaymentOption = paymentOptionService.createPaymentOption(paymentOption);

        assertNotNull(createdPaymentOption);
        assertEquals("New Payment", createdPaymentOption.getName());
        verify(paymentOptionRepository).save(paymentOption);
    }

    @Test
    void testUpdatePaymentOption_PaymentOptionExists() {
        PaymentOption existingPaymentOption = new PaymentOption.PaymentOptionBuilder().cardNumber("1234")
                .name("Existing Payment").buildPaymentOption();
        PaymentOption updatedPaymentOption = new PaymentOption.PaymentOptionBuilder().cardNumber("5678")
                .name("Updated Payment").buildPaymentOption();

        when(paymentOptionRepository.findByCardNumber("1234")).thenReturn(Optional.of(existingPaymentOption));
        when(paymentOptionRepository.save(existingPaymentOption)).thenReturn(existingPaymentOption);

        Optional<PaymentOption> result = paymentOptionService.updatePaymentOption("1234", updatedPaymentOption);

        assertTrue(result.isPresent());
        assertEquals("5678", existingPaymentOption.getCardNumber());
        assertEquals("Updated Payment", existingPaymentOption.getName());
        verify(paymentOptionRepository).save(existingPaymentOption);
    }

    @Test
    void testUpdatePaymentOption_PaymentOptionNotFound() {
        when(paymentOptionRepository.findByCardNumber("9999")).thenReturn(Optional.empty());

        Optional<PaymentOption> result = paymentOptionService.updatePaymentOption("9999",
                new PaymentOption.PaymentOptionBuilder().buildPaymentOption());

        assertFalse(result.isPresent());
        verify(paymentOptionRepository, never()).save(any(PaymentOption.class));
    }

    @Test
    void testGetPaymentOption_PaymentOptionExists() {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder().buildPaymentOption();

        when(paymentOptionRepository.findById(1L)).thenReturn(Optional.of(paymentOption));

        Optional<PaymentOption> result = paymentOptionService.getPaymentOption(1L);

        assertTrue(result.isPresent());
        assertEquals(paymentOption, result.get());
    }

    @Test
    void testGetPaymentOption_PaymentOptionNotFound() {
        when(paymentOptionRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<PaymentOption> result = paymentOptionService.getPaymentOption(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindByName_PaymentOptionExists() {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder().name("Test Payment")
                .buildPaymentOption();

        when(paymentOptionRepository.findByName("Test Payment")).thenReturn(Optional.of(paymentOption));

        Optional<PaymentOption> result = paymentOptionService.findByName("Test Payment");

        assertTrue(result.isPresent());
        assertEquals(paymentOption, result.get());
    }

    @Test
    void testDeletePaymentOption() {
        paymentOptionService.deletePaymentOption(1L);
        verify(paymentOptionRepository).deleteById(1L);
    }
}
