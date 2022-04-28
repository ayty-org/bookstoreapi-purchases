package br.com.bookstoreapi.purchases.purchase.service;

import java.util.UUID;

@FunctionalInterface
public interface ExistPurchaseByClientUuid {

    boolean existsByClientUuid(UUID id);
}
