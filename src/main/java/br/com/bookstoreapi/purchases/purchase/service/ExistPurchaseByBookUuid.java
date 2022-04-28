package br.com.bookstoreapi.purchases.purchase.service;

import java.util.UUID;

@FunctionalInterface
public interface ExistPurchaseByBookUuid {

    boolean existsByBookUuid(UUID uuid);
}
