package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;

import java.util.UUID;

@FunctionalInterface
public interface DeletePurchaseService {

    void delete(UUID id) throws EntityNotFoundException;

}
