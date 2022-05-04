package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;

import java.util.UUID;

@FunctionalInterface
public interface GetPurchaseService {

    PurchaseResultDTO getByUuid(UUID id) throws EntityNotFoundException;
}
