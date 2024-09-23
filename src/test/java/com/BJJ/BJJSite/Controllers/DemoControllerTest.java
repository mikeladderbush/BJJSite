package com.BJJ.BJJSite.Controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import com.BJJ.BJJSite.Security.SecurityHelper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void testSayHello() throws Exception {
        SecurityHelper.setupSecurityContext("defaultUser", "password", "user:read");
        mockMvc.perform(get("/api/v1/demo-controller"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from secured endpoint"));
    }

}
