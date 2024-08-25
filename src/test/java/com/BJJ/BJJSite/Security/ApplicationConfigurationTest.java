package com.BJJ.BJJSite.Security;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationConfigurationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAllRequestsPermitted() throws Exception {
        // Test that a request to any endpoint is permitted
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void testSessionIsStateless() throws Exception {
        // Test that session is stateless by checking that no session is created
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(request().sessionAttributeDoesNotExist("JSESSIONID"));
    }

    @Test
    @WithMockUser
    void testFrameOptionsSameOrigin() throws Exception {
        // Test that frame options are set to sameOrigin
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(header().string("X-Frame-Options", "SAMEORIGIN"));
    }
}
