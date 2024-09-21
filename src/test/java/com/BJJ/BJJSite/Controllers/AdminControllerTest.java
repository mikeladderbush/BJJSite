package com.BJJ.BJJSite.Controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "ADMIN", authorities = "admin:read")
    void testGet() throws Exception {
        mockMvc.perform(get("/api/v1/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("GET:: admin controller"));
    }

    @Test
    @WithMockUser(roles = "ADMIN", authorities = "admin:create")
    void testPost() throws Exception {
        mockMvc.perform(post("/api/v1/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("POST:: admin controller"));
    }

    @Test
    @WithMockUser(roles = "ADMIN", authorities = "admin:update")
    void testPut() throws Exception {
        mockMvc.perform(put("/api/v1/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("PUT:: admin controller"));
    }

    @Test
    @WithMockUser(roles = "ADMIN", authorities = "admin:delete")
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("DELETE:: admin controller"));
    }

    @Test
    @WithMockUser(roles = "USER") // Not an admin
    void testForbiddenForNonAdmin() throws Exception {
        mockMvc.perform(get("/api/v1/admin"))
                .andExpect(status().isForbidden());
    }
}
