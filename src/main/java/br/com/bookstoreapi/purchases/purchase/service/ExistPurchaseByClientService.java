package br.com.bookstoreapi.purchases.purchase.service;

import java.util.UUID;

@FunctionalInterface
public interface ExistPurchaseByClientService {

    boolean existsByClientUuid(UUID id);

}
