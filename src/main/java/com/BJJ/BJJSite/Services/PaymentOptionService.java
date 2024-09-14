package com.BJJ.BJJSite.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.FactoryExceptions.PaymentOptionAlreadyExistsException;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;

import java.util.Optional;

/**
 * Service class for managing PaymentOption entities.
 * 
 * This class provides methods to create, update, retrieve, and delete PaymentOptions, while handling business logic and interactions with the PaymentOptionRepository.
 */
@Service
public class PaymentOptionService {

    private final PaymentOptionRepository paymentOptionRepository;

    /**
     * Constructor for PaymentOptionService.
     * 
     * @param paymentOptionRepository The repository used to interact with PaymentOption data.
     */
    @Autowired
    public PaymentOptionService(PaymentOptionRepository paymentOptionRepository) {
        this.paymentOptionRepository = paymentOptionRepository;
    }

    /**
     * Creates a new PaymentOption.
     * 
     * If a PaymentOption with the same name already exists, it updates the existing one instead.
     * 
     * @param paymentOption The PaymentOption to be created.
     * @return The created PaymentOption.
     * @throws PaymentOptionAlreadyExistsException If a PaymentOption with the same name already exists.
     */
    @Transactional
    public PaymentOption createPaymentOption(PaymentOption paymentOption) throws PaymentOptionAlreadyExistsException {
        Optional<PaymentOption> existingPaymentOption = paymentOptionRepository.findByName(paymentOption.getName());
        if (existingPaymentOption.isPresent()) {
            System.out.println("PaymentOption with number " + paymentOption.getCardNumber() + " already exists.");
            updatePaymentOption(existingPaymentOption.get().getCardNumber(), paymentOption);
        }
        return paymentOptionRepository.save(paymentOption);
    }

    /**
     * Updates an existing PaymentOption based on the card number.
     * 
     * This method updates the fields of an existing PaymentOption with the values from the provided update object.
     * 
     * @param cardNumber The card number of the PaymentOption to be updated.
     * @param update The updated PaymentOption data.
     * @return An Optional containing the updated PaymentOption if found, or an empty Optional if not found.
     */
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

    /**
     * Retrieves a PaymentOption by its ID.
     * 
     * @param id The ID of the PaymentOption.
     * @return An Optional containing the PaymentOption if found, or an empty Optional if not found.
     */
    public Optional<PaymentOption> getPaymentOption(Long id) {
        return paymentOptionRepository.findById(id);
    }

    /**
     * Finds a PaymentOption by its name.
     * 
     * @param paymentOptionName The name of the PaymentOption.
     * @return An Optional containing the PaymentOption if found, or an empty Optional if not found.
     */
    public Optional<PaymentOption> findByName(String paymentOptionName) {
        return paymentOptionRepository.findByName(paymentOptionName);
    }

    /**
     * Deletes a PaymentOption by its ID.
     * 
     * @param id The ID of the PaymentOption to be deleted.
     */
    public void deletePaymentOption(Long id) {
        paymentOptionRepository.deleteById(id);
    }
}
