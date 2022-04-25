package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.UUID;

@FunctionalInterface
public interface GetPurchaseService {

    Purchase getByUuid(UUID id) throws EntityNotFoundException;
}
