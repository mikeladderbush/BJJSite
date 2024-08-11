package com.BJJ.BJJSite.Factories.FactoryExceptions;

public class PaymentOptionAlreadyExistsException extends RuntimeException {
    public PaymentOptionAlreadyExistsException(String message) {
        super(message);
    }
}
