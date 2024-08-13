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

@RestController
@RequestMapping("/api/users/paymentoptions")
public class PaymentOptionController {
    
    private final PaymentOptionFactory paymentOptionFactory;
    private final PaymentOptionRepository paymentOptionRepository;

    @Autowired
    public PaymentOptionController(PaymentOptionFactory paymentOptionFactory, PaymentOptionRepository paymentOptionRepository) {
        this.paymentOptionFactory = paymentOptionFactory;
        this.paymentOptionRepository = paymentOptionRepository;
    }

    @Autowired
    private PaymentOptionService paymentOptionService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentOption> getPaymentOptionById(@PathVariable Long id) {
        Optional<PaymentOption> paymentOptionOptional = paymentOptionService.getPaymentOption(id);
        return paymentOptionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public Optional<PaymentOption> createPaymentOption() {
        return paymentOptionFactory.createPaymentOption();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentOption> updatePaymentOption(@PathVariable String cardNumber, @RequestBody PaymentOption update) {
        Optional<PaymentOption> paymentOptionOptional = paymentOptionRepository.findByCardNumber(cardNumber);
        paymentOptionService.updatePaymentOption(paymentOptionOptional.get().getCardNumber(), update);
        return paymentOptionOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentOption(@PathVariable Long id) {
        paymentOptionService.deletePaymentOption(id);
        return ResponseEntity.ok().build();
    }

}
