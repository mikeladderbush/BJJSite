package com.BJJ.BJJSite.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BJJ.BJJSite.Classes.PaymentOption;
import com.BJJ.BJJSite.Factories.PaymentOptionFactory;
import com.BJJ.BJJSite.Repositories.PaymentOptionRepository;
import com.BJJ.BJJSite.Services.PaymentOptionService;

/**
 * REST controller for managing PaymentOptions.
 * 
 * Provides endpoints to create, retrieve, update, and delete payment options associated with users.
 */
@RestController
@RequestMapping("/api/users/paymentoptions")
public class PaymentOptionController {
    
    private final PaymentOptionFactory paymentOptionFactory;
    private final PaymentOptionRepository paymentOptionRepository;

    /**
     * Constructor for PaymentOptionController.
     * 
     * @param paymentOptionFactory The factory used to create PaymentOption instances.
     * @param paymentOptionRepository The repository used to interact with the PaymentOption data.
     */
    @Autowired
    public PaymentOptionController(PaymentOptionFactory paymentOptionFactory, PaymentOptionRepository paymentOptionRepository) {
        this.paymentOptionFactory = paymentOptionFactory;
        this.paymentOptionRepository = paymentOptionRepository;
    }

    @Autowired
    private PaymentOptionService paymentOptionService;

    /**
     * Retrieves a PaymentOption by its ID.
     * 
     * @param id The ID of the PaymentOption.
     * @return A ResponseEntity containing the PaymentOption if found, or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PaymentOption> getPaymentOptionById(@PathVariable Long id) {
        Optional<PaymentOption> paymentOptionOptional = paymentOptionService.getPaymentOption(id);
        return paymentOptionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new PaymentOption.
     * 
     * @return An Optional containing the created PaymentOption.
     */
    @PostMapping("/{id}")
    public Optional<PaymentOption> createPaymentOption() {
        return paymentOptionFactory.createPaymentOption();
    }

    /**
     * Updates an existing PaymentOption.
     * 
     * @param cardNumber The card number of the PaymentOption to be updated.
     * @param update The updated PaymentOption data.
     * @return A ResponseEntity containing the updated PaymentOption if found, or a 404 status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PaymentOption> updatePaymentOption(@PathVariable String cardNumber, @RequestBody PaymentOption update) {
        Optional<PaymentOption> paymentOptionOptional = paymentOptionRepository.findByCardNumber(cardNumber);
        paymentOptionService.updatePaymentOption(paymentOptionOptional.get().getCardNumber(), update);
        return paymentOptionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Deletes a PaymentOption by its ID.
     * 
     * @param id The ID of the PaymentOption to be deleted.
     * @return A ResponseEntity with a 200 status if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentOption(@PathVariable Long id) {
        paymentOptionService.deletePaymentOption(id);
        return ResponseEntity.ok().build();
    }

}
