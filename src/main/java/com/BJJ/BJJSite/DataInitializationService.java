package com.BJJ.BJJSite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Classes.Role;
import com.BJJ.BJJSite.Classes.User;
import com.BJJ.BJJSite.Factories.PaymentOptionFactory;
import com.BJJ.BJJSite.Factories.UserFactory;
import com.BJJ.BJJSite.Services.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Service class for initializing data during application startup.
 * 
 * This service is responsible for creating demo users and associated payment options, as well as populating the database with initial data.
 */
@Service
public class DataInitializationService {

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private PaymentOptionFactory paymentOptionFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Initializes the application data after the service is constructed.
     * 
     * This method is executed automatically after the service is constructed and is annotated with @PostConstruct.
     * It creates a demo user with associated payment options and roles, and then saves them to the database.
     */
    @PostConstruct
    @Transactional
    public void init() {
        System.out.println("DataInitializationService is running...");

        Optional<User> demoUser = userFactory.createUser(builder -> {
            builder.firstname("Mike")
                    .lastname("Ladderbush")
                    .username("mikeladder")
                    .password(passwordEncoder.encode("mike")) // Encode the password
                    .email("mikeladderbush@gmail.com")
                    .phone("9787708430")
                    .address("boston")
                    .sex("male")
                    .dob("2241997")
                    .paymentOptions(new ArrayList<>())
                    .role(Role.USER);
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
            System.out.println("User creation failed.");
        }

    }
}
