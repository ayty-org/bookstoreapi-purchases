package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeletePurchaseService {

    void delete(UUID id) throws EntityNotFoundException;

}
