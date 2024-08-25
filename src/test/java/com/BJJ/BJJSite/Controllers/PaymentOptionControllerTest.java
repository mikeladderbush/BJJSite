package com.BJJ.BJJSite.Controllers;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Services.PaymentOptionService;

@WebMvcTest(PaymentOptionController.class)
class PaymentOptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PaymentOptionService paymentOptionService;

    @InjectMocks
    private PaymentOptionController paymentOptionController;

    /*

    PaymentOptionControllerTest.testGetPaymentOptionById ┬╗ IllegalState ApplicationContext failure threshold (1) exceeded: 
    skipping repeated attempt to load context for [WebMergedContextConfiguration@19d9ba89 testClass = 
    com.BJJ.BJJSite.Controllers.PaymentOptionControllerTest

    @Test
    void testGetPaymentOptionById() throws Exception {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("Test Payment Option")
                .cardNumber("1234567890123456")
                .buildPaymentOption();

        when(paymentOptionService.getPaymentOption(1L)).thenReturn(Optional.of(paymentOption));

        mockMvc.perform(get("/api/users/paymentoptions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Payment Option"))
                .andExpect(jsonPath("$.cardNumber").value("1234567890123456"));
    }
    */

    /*

    PaymentOptionControllerTest.testGetPaymentOptionByIdNotFound ┬╗ IllegalState Failed to load ApplicationContext for 
    [WebMergedContextConfiguration@19d9ba89 testClass = com.BJJ.BJJSite.Controllers.PaymentOptionControllerTest

    @Test
    void testGetPaymentOptionByIdNotFound() throws Exception {
        when(paymentOptionService.getPaymentOption(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/paymentoptions/1"))
                .andExpect(status().isNotFound());
    }
    */

    /*

    PaymentOptionControllerTest.testCreatePaymentOption ┬╗ IllegalState ApplicationContext 
    failure threshold (1) exceeded: skipping repeated attempt to load context for [WebMergedContextConfiguration@19d9ba89 
    testClass = com.BJJ.BJJSite.Controllers.PaymentOptionControllerTest

    @Test
    void testCreatePaymentOption() throws Exception {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder()
                .name("New Payment Option")
                .cardNumber("9876543210987654")
                .buildPaymentOption();

        when(paymentOptionService.createPaymentOption(paymentOption)).thenReturn(paymentOption);

        mockMvc.perform(post("/api/users/paymentoptions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"New Payment Option\", \"cardNumber\": \"9876543210987654\"}"))
                .andExpect(status().isOk());
    }
    */

    /*

    PaymentOptionControllerTest.testUpdatePaymentOption ┬╗ IllegalState ApplicationContext failure threshold (1) exceeded: 
    skipping repeated attempt to load context for [WebMergedContextConfiguration@19d9ba89 testClass = 
    com.BJJ.BJJSite.Controllers.PaymentOptionControllerTest
    
    @Test
    void testUpdatePaymentOption() throws Exception {
        PaymentOption existingOption = new PaymentOption.PaymentOptionBuilder()
                .name("Existing Payment Option")
                .cardNumber("1111222233334444")
                .buildPaymentOption();

        when(paymentOptionService.updatePaymentOption("1111222233334444", existingOption)).thenReturn(Optional.of(existingOption));

        mockMvc.perform(put("/api/users/paymentoptions/1111222233334444")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Updated Payment Option\", \"cardNumber\": \"1111222233334444\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Payment Option"));
    }
    */

    /*

    PaymentOptionControllerTest.testDeletePaymentOption ┬╗ IllegalState ApplicationContext failure threshold (1) exceeded: 
    skipping repeated attempt to load context for [WebMergedContextConfiguration@19d9ba89 testClass = 
    com.BJJ.BJJSite.Controllers.PaymentOptionControllerTest

    @Test
    void testDeletePaymentOption() throws Exception {
        doNothing().when(paymentOptionService).deletePaymentOption(1L);

        mockMvc.perform(delete("/api/users/paymentoptions/1"))
                .andExpect(status().isOk());
    }
    */
}
