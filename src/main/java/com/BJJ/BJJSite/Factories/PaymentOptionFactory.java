package com.BJJ.BJJSite.Factories;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Services.PaymentOptionService;

/**
 * Factory class for creating instances of PaymentOption.
 * 
 * This factory handles the creation of PaymentOption objects, including the handling of scenarios where a payment option might already exist.
 */
@Component
public class PaymentOptionFactory {

    @Autowired
    private PaymentOptionService paymentOptionService;

    /**
     * Creates a new PaymentOption with default values.
     * 
     * If a PaymentOption with the same attributes already exists, this method returns an empty Optional.
     * 
     * @return An Optional containing the created PaymentOption, or an empty Optional if the PaymentOption already exists.
     */
    public Optional<PaymentOption> createPaymentOption() {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder().buildPaymentOption();
        try {
            return Optional.of(paymentOptionService.createPaymentOption(paymentOption));
        } catch (PaymentOptionAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    /**
     * Creates a new PaymentOption using a custom configuration provided by a Consumer.
     * 
     * @param consumer A Consumer that applies custom configurations to the PaymentOptionBuilder.
     * @return The created PaymentOption.
     */
    public PaymentOption createPaymentOption(Consumer<PaymentOption.PaymentOptionBuilder> consumer) {
        PaymentOption.PaymentOptionBuilder builder = new PaymentOption.PaymentOptionBuilder();
        consumer.accept(builder);
        return builder.buildPaymentOption();
    }
}
