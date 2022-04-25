package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;

@FunctionalInterface
public interface SavePurchaseService {

    Purchase save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException;
}
