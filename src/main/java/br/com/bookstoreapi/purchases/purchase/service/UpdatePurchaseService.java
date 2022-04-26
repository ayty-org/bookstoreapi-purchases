package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;

import java.util.UUID;

@FunctionalInterface
public interface UpdatePurchaseService {

    Purchase update(UUID id, Purchase purchase)throws EntityNotFoundException, BookOutOfStockException;
}
