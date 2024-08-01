package com.BJJ.BJJSite.Services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Interfaces.ServiceInterface;


public class PaymentOptionService implements ServiceInterface {

    private List<PaymentOption> paymentOptions;

    public PaymentOptionService() {
        this.paymentOptions = new ArrayList<>();
    }

    public void addPaymentOption(PaymentOption paymentOption) {
        paymentOptions.add(paymentOption);
    }

    public Long generateId() {
        return ServiceInterface.super.generateId();
    }

    public void deletePaymentOption(String cardNumber) {
        Iterator<PaymentOption> iterator = paymentOptions.iterator();
        while (iterator.hasNext()) {
            PaymentOption paymentOption = iterator.next();
            if (paymentOption.getCardNumber().equals(cardNumber)) {
                iterator.remove();
            }
        }
    }

    public List<PaymentOption> getAllPaymentOptions() {
        return paymentOptions;
    }

    public PaymentOption findPaymentOptionByCardNumber(String cardNumber) {
        for (PaymentOption paymentOption : paymentOptions) {
            if (paymentOption.getCardNumber().equals(cardNumber)) {
                return paymentOption;
            }
        }
        return null;
    }

}
