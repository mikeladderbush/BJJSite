package com.BJJ.BJJSite.Config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WebConfig.class)
class WebConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCorsConfigurationInvalidOrigin() throws Exception {
        mockMvc.perform(get("/api/test-endpoint")
                .header("Origin", "http://invalid-origin.com"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testViewControllerConfiguration() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/index.html"));
    }
}
