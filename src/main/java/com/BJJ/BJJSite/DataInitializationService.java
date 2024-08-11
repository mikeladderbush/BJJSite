package com.BJJ.BJJSite;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.PaymentOptionFactory;
import com.BJJ.BJJSite.Factories.UserFactory;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.annotation.PostConstruct;

@Service
public class DataInitializationService {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private PaymentOptionFactory paymentOptionFactory;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("DataInitializationService is running...");
    
        Optional<User> demoUser = userFactory.createUser(builder -> {
            builder.fullName("Mike Ladderbush")
                    .username("mikeladderbush")
                    .password("mike")
                    .email("mikeladderbush@gmail.com")
                    .phone("9787708430")
                    .address("boston")
                    .sex("male")
                    .dob("2241997")
                    .paymentOptions(new ArrayList<>());
        });
    
        PaymentOption demoPaymentOption = paymentOptionFactory.createPaymentOption(builder -> {
            builder.billingAddress("Demo")
                    .billingZipCode("Demo")
                    .cardNumber("Demo")
                    .cardType("Demo")
                    .confirmationCode("Demo")
                    .expirationDate("Demo")
                    .name("Demo");
        });
    
        demoUser.ifPresent(user -> {
            user.addPaymentOption(demoPaymentOption);
            System.out.println("Saving user: " + user.getEmail());
            userService.updateUser(user.getUserId(), user);
            System.out.println("User saved successfully");
        });
    
    }
}
