package br.com.bookstoreapi.purchases.purchase.service;

import java.util.UUID;

@FunctionalInterface
public interface ExistPurchaseByBookService {

    boolean existsByBookUuid(UUID uuid);

}
