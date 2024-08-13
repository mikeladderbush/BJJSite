package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;

import java.util.Optional;

@Service
public class PaymentOptionService {

    private final PaymentOptionRepository paymentOptionRepository;

    @Autowired
    public PaymentOptionService(PaymentOptionRepository paymentOptionRepository) {
        this.paymentOptionRepository = paymentOptionRepository;
    }

    @Transactional
    public PaymentOption createPaymentOption(PaymentOption paymentOption) throws PaymentOptionAlreadyExistsException {
        Optional<PaymentOption> existingPaymentOption = paymentOptionRepository.findByName(paymentOption.getName());
        if (existingPaymentOption.isPresent()) {
            System.out.println("PaymentOption with number " + paymentOption.getCardNumber() + " already exists.");
            updatePaymentOption(existingPaymentOption.get().getCardNumber(), paymentOption);
        }
        return paymentOptionRepository.save(paymentOption);
    }

    public Optional<PaymentOption> updatePaymentOption(String cardNumber, PaymentOption update) {
        return paymentOptionRepository.findByCardNumber(cardNumber).map(paymentOption -> {
            if (update.getCardNumber() != null) {
                paymentOption.setCardNumber(update.getCardNumber());
            }
            if (update.getName() != null) {
                paymentOption.setName(update.getName());
            }
            if (update.getExpirationDate() != null) {
                paymentOption.setExpirationDate(update.getExpirationDate());
            }
            if (update.getConfirmationCode() != null) {
                paymentOption.setConfirmationCode(update.getConfirmationCode());
            }
            if (update.getCardType() != null) {
                paymentOption.setCardType(update.getCardType());
            }
            if (update.getBillingAddress() != null) {
                paymentOption.setBillingAddress(update.getBillingAddress());
            }
            if (update.getBillingZipCode() != null) {
                paymentOption.setBillingZipCode(update.getBillingZipCode());
            }
            return paymentOptionRepository.save(paymentOption);
        });
    }

    public Optional<PaymentOption> getPaymentOption(Long id) {
        return paymentOptionRepository.findById(id);
    }

    public Optional<PaymentOption> findByName(String paymentOptionname) {
        return paymentOptionRepository.findByName(paymentOptionname);
    }

    public void deletePaymentOption(Long id) {
        paymentOptionRepository.deleteById(id);
    }
}
