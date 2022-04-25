package com.bookstoreapi.bookstoreapi.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Optional<Purchase> findByUuid(UUID id);
    boolean existsByPurchasedBooksUuid(UUID id);
    boolean existsByClientUuid(UUID id);
}
