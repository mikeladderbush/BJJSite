package com.BJJ.BJJSite.Controllers;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;
import com.BJJ.BJJSite.Services.PaymentOptionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentOptionController.class)
class PaymentOptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentOptionService paymentOptionService;

    @MockBean
    private PaymentOptionRepository paymentOptionRepository;

    @Test
    void testGetPaymentOptionById_ReturnsOk() throws Exception {
        PaymentOption mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/23")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        when(paymentOptionService.getPaymentOption(anyLong())).thenReturn(Optional.of(mockPaymentOption));

        mockMvc.perform(get("/api/users/paymentoptions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cardNumber").value("4111111111111111"));
    }

    @Test
    void testGetPaymentOptionById_ReturnsNotFound() throws Exception {
        when(paymentOptionService.getPaymentOption(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/paymentoptions/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCreatePaymentOption() throws Exception {
        PaymentOption mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("John Doe")
                .cardNumber("4111111111111111")
                .expirationDate("12/23")
                .confirmationCode("123")
                .cardType("Visa")
                .billingAddress("123 Main St")
                .billingZipCode("12345")
                .buildPaymentOption();

        when(paymentOptionRepository.save(mockPaymentOption)).thenReturn(mockPaymentOption);

        mockMvc.perform(post("/api/users/paymentoptions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

@Test
void testUpdatePaymentOption() throws Exception {
    // Prepare the mock PaymentOption object
    PaymentOption mockPaymentOption = new PaymentOption.PaymentOptionBuilder()
            .name("John Doe")
            .cardNumber("4111111111111111")
            .expirationDate("12/23")
            .confirmationCode("123")
            .cardType("Visa")
            .billingAddress("123 Main St")
            .billingZipCode("12345")
            .buildPaymentOption();

    // Create an updated PaymentOption with only the name changed
    PaymentOption updatedPaymentOption = new PaymentOption.PaymentOptionBuilder()
            .name("Updated Name")  // Only the name is updated
            .cardNumber("4111111111111111")
            .expirationDate("12/23")
            .confirmationCode("123")
            .cardType("Visa")
            .billingAddress("123 Main St")
            .billingZipCode("12345")
            .buildPaymentOption();

    // Mock the behavior of finding the PaymentOption by card number
    when(paymentOptionRepository.findByCardNumber(anyString())).thenReturn(Optional.of(mockPaymentOption));

    // Mock the updatePaymentOption method to return the updated payment option wrapped in Optional
    when(paymentOptionService.updatePaymentOption(anyString(), any(PaymentOption.class)))
            .thenReturn(Optional.of(updatedPaymentOption));

    // Perform the PUT request and verify the updated name field
    mockMvc.perform(put("/api/users/paymentoptions/4111111111111111")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"Updated Name\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("Updated Name"));
}


    @Test
    void testUpdatePaymentOption_NotFound() throws Exception {
        when(paymentOptionRepository.findByCardNumber(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/users/paymentoptions/4111111111111111")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Updated Name\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeletePaymentOption() throws Exception {
        mockMvc.perform(delete("/api/users/paymentoptions/1"))
                .andExpect(status().isOk());
    }
}
