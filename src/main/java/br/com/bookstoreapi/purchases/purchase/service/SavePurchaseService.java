package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {

    Purchase save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException;
}
