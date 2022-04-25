package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.BookOutOfStockException;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.UUID;

@FunctionalInterface
public interface UpdatePurchaseService {

    Purchase update(UUID id, Purchase purchase)throws EntityNotFoundException, BookOutOfStockException;
}
