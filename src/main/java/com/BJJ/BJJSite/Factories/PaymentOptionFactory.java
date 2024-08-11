package com.BJJ.BJJSite.Factories;

import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Services.PaymentOptionService;

@Component
public class PaymentOptionFactory {

    @Autowired
    private PaymentOptionService paymentOptionService;

    public Optional<PaymentOption> createPaymentOption() {
        PaymentOption paymentOption = new PaymentOption.PaymentOptionBuilder().buildPaymentOption();
        try {
            return Optional.of(paymentOptionService.createPaymentOption(paymentOption));
        } catch (PaymentOptionAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    public PaymentOption createPaymentOption(Consumer<PaymentOption.PaymentOptionBuilder> consumer) {
        PaymentOption.PaymentOptionBuilder builder = new PaymentOption.PaymentOptionBuilder();
        consumer.accept(builder);
        return builder.buildPaymentOption();
    }
}
