package com.BJJ.BJJSite.Services;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentOptionServiceTest {

    @Mock
    private PaymentOptionRepository paymentOptionRepository;

    @InjectMocks
    private PaymentOptionService paymentOptionService;

    private PaymentOption mockPaymentOption;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Test Payment Option")
                .cardNumber("1234567890123456")
                .expirationDate("12/25")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();
    }

    @Test
    void testCreatePaymentOption_Success() throws PaymentOptionAlreadyExistsException {
        // Mock that no existing payment option exists with the same name
        when(paymentOptionRepository.findByName("Test Payment Option")).thenReturn(Optional.empty());
        when(paymentOptionRepository.save(mockPaymentOption)).thenReturn(mockPaymentOption);

        // Call the createPaymentOption method
        PaymentOption createdPaymentOption = paymentOptionService.createPaymentOption(mockPaymentOption);

        assertNotNull(createdPaymentOption);
        assertEquals(mockPaymentOption.getName(), createdPaymentOption.getName());
        verify(paymentOptionRepository, times(1)).findByName("Test Payment Option");
        verify(paymentOptionRepository, times(1)).save(mockPaymentOption);
    }

    @Test
    void testCreatePaymentOption_UpdateExisting() throws PaymentOptionAlreadyExistsException {
        // Mock that an existing payment option exists with the same name
        PaymentOption existingPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Test Payment Option")
                .cardNumber("1111222233334444")
                .expirationDate("12/23")
                .confirmationCode("987")
                .cardType("MasterCard")
                .billingAddress("456 Secondary St")
                .billingZipCode("67890")
                .buildPaymentOption();

        when(paymentOptionRepository.findByName("Test Payment Option")).thenReturn(Optional.of(existingPaymentOption));
        when(paymentOptionRepository.save(mockPaymentOption)).thenReturn(mockPaymentOption);

        // Call the createPaymentOption method, expecting it to update the existing one
        PaymentOption createdPaymentOption = paymentOptionService.createPaymentOption(mockPaymentOption);

        assertNotNull(createdPaymentOption);
        assertEquals(mockPaymentOption.getCardNumber(), createdPaymentOption.getCardNumber());
        verify(paymentOptionRepository, times(1)).findByName("Test Payment Option");
        verify(paymentOptionRepository, times(1)).save(mockPaymentOption);
    }

    @Test
    void testUpdatePaymentOption_Success() {
        // Mock finding an existing payment option
        when(paymentOptionRepository.findByCardNumber("1234567890123456")).thenReturn(Optional.of(mockPaymentOption));

        // Mock the updated payment option with new details
        PaymentOption updatedPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Updated Payment Option")
                .cardNumber("1234567890123456")
                .expirationDate("12/26")
                .confirmationCode("456")
                .cardType("MasterCard")
                .billingAddress("789 Tertiary St")
                .billingZipCode("54321")
                .buildPaymentOption();

        when(paymentOptionRepository.save(mockPaymentOption)).thenReturn(mockPaymentOption);

        Optional<PaymentOption> updatedOption = paymentOptionService.updatePaymentOption("1234567890123456", updatedPaymentOption);

        assertTrue(updatedOption.isPresent());
        assertEquals("Updated Payment Option", updatedOption.get().getName());
        verify(paymentOptionRepository, times(1)).findByCardNumber("1234567890123456");
        verify(paymentOptionRepository, times(1)).save(mockPaymentOption);
    }

    @Test
    void testGetPaymentOptionById() {
        // Mock the repository's findById method
        when(paymentOptionRepository.findById(1L)).thenReturn(Optional.of(mockPaymentOption));

        Optional<PaymentOption> foundPaymentOption = paymentOptionService.getPaymentOption(1L);

        assertTrue(foundPaymentOption.isPresent());
        assertEquals(mockPaymentOption.getName(), foundPaymentOption.get().getName());
        verify(paymentOptionRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByName() {
        // Mock the repository's findByName method
        when(paymentOptionRepository.findByName("Test Payment Option")).thenReturn(Optional.of(mockPaymentOption));

        Optional<PaymentOption> foundPaymentOption = paymentOptionService.findByName("Test Payment Option");

        assertTrue(foundPaymentOption.isPresent());
        assertEquals(mockPaymentOption.getName(), foundPaymentOption.get().getName());
        verify(paymentOptionRepository, times(1)).findByName("Test Payment Option");
    }

    @Test
    void testDeletePaymentOptionById() {
        // Mock the repository's deleteById method
        doNothing().when(paymentOptionRepository).deleteById(1L);

        paymentOptionService.deletePaymentOption(1L);

        verify(paymentOptionRepository, times(1)).deleteById(1L);
    }
}
