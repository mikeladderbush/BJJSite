package com.BJJ.BJJSite.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DemoController.class)
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser // Simulate an authenticated user
    void testSayHello() throws Exception {
        mockMvc.perform(get("/api/v1/demo-controller"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from secured endpoint"));
    }

    @Test
    void testSayHelloUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/demo-controller"))
                .andExpect(status().isUnauthorized());
    }
}
