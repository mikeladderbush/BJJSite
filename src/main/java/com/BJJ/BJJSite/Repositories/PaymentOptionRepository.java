package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.PaymentOption;

/**
 * Repository interface for managing PaymentOption entities.
 * 
 * This interface extends JpaRepository and provides methods to retrieve PaymentOption entities by various attributes such as name, ID, and card number.
 */
@Repository
public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {

    /**
     * Finds a PaymentOption by its name.
     * 
     * @param name The name of the PaymentOption.
     * @return An Optional containing the PaymentOption if found, or an empty Optional if not found.
     */
    Optional<PaymentOption> findByName(String name);

    /**
     * Finds a PaymentOption by its ID.
     * 
     * @param id The ID of the PaymentOption.
     * @return An Optional containing the PaymentOption if found, or an empty Optional if not found.
     */
    Optional<PaymentOption> findById(Long id);

    /**
     * Finds a PaymentOption by its card number.
     * 
     * @param cardNumber The card number of the PaymentOption.
     * @return An Optional containing the PaymentOption if found, or an empty Optional if not found.
     */
    Optional<PaymentOption> findByCardNumber(String cardNumber);
}
