package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;

@FunctionalInterface
public interface SavePurchaseService {

    PurchaseResultDTO save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException;
}
