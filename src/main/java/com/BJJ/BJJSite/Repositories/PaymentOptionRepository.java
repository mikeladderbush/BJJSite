package com.BJJ.BJJSite.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BJJ.BJJSite.Classes.PaymentOption;

@Repository
public interface PaymentOptionRepository extends JpaRepository<PaymentOption, Long> {
    Optional<PaymentOption> findByName(String name);
    Optional<PaymentOption> findById(Long id);
}
