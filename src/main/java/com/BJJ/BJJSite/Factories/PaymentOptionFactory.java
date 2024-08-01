package com.BJJ.BJJSite.Factories;

import java.util.function.Consumer;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Services.PaymentOptionService;

public class PaymentOptionFactory {

    private static final PaymentOptionService paymentOptionService = new PaymentOptionService();

    // Method to create a PaymentOption with default values
    public static PaymentOption createPaymentOption() {
        Long id = paymentOptionService.generateId();
        return new PaymentOption.PaymentOptionBuilder(id).buildPaymentOption();
    }

    public static PaymentOption createPaymentOption(Consumer<PaymentOption.PaymentOptionBuilder> consumer) {
        Long id = paymentOptionService.generateId();
        PaymentOption.PaymentOptionBuilder builder = new PaymentOption.PaymentOptionBuilder(id);
        consumer.accept(builder);
        return builder.buildPaymentOption();
    }
}
