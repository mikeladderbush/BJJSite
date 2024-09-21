package com.BJJ.BJJSite.Security.Authentication;

import com.BJJ.BJJSite.Security.Authentication.AuthenticationController;
import com.BJJ.BJJSite.Security.Authentication.AuthenticationRequest;
import com.BJJ.BJJSite.Security.Authentication.AuthenticationResponse;
import com.BJJ.BJJSite.Security.Authentication.AuthenticationService;
import com.BJJ.BJJSite.Security.Authentication.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void testRegister_Success() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest("john", "doe", "john.doe@example.com", "password123");
        AuthenticationResponse mockResponse = new AuthenticationResponse("mock-token");

        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstname\":\"john\",\"lastname\":\"doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-token"));
    }

    @Test
    void testAuthenticate_Success() throws Exception {
        AuthenticationRequest authRequest = new AuthenticationRequest("john.doe@example.com", "password123");
        AuthenticationResponse mockResponse = new AuthenticationResponse("mock-token");

        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(mockResponse);

        mockMvc.perform(post("/api/v1/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("mock-token"));
    }
}
