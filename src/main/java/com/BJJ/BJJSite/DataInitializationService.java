package com.BJJ.BJJSite;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.PaymentOptionFactory;
import com.BJJ.BJJSite.Factories.UserFactory;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class DataInitializationService {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private PaymentOptionFactory paymentOptionFactory;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("DataInitializationService is running...");

        Optional<User> demoUser = userFactory.createUser(builder -> {
            builder.fullName("Mike Ladderbush")
                    .username("mikeladder")
                    .password("mike")
                    .email("mikeladderbush@gmail.com")
                    .phone("9787708430")
                    .address("boston")
                    .sex("male")
                    .dob("2241997")
                    .paymentOptions(new ArrayList<>());
        });

        if (demoUser.isPresent()) {
            // Create a new PaymentOption using the factory
            PaymentOption demoPaymentOption = paymentOptionFactory.createPaymentOption(builder -> {
                builder.billingAddress("Demo")
                        .billingZipCode("Demo")
                        .cardNumber("Demo")
                        .cardType("Demo")
                        .confirmationCode("Demo")
                        .expirationDate("Demo")
                        .name("Demo");
            });

            // Associate the PaymentOption with the User
            demoPaymentOption.setUser(demoUser.get());

            // Add the PaymentOption to the User's collection
            demoUser.get().addPaymentOption(demoPaymentOption);

            // Save the User, which will also save the PaymentOption due to cascading
            userService.createUser(demoUser.get());
            System.out.println("User saved successfully with payment option.");
        } else {
            System.out.println("User not found.");
        }
    }
}
