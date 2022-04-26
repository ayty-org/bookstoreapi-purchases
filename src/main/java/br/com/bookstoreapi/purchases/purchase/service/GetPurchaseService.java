package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;

import java.util.UUID;

@FunctionalInterface
public interface GetPurchaseService {

    Purchase getByUuid(UUID id) throws EntityNotFoundException;
}
