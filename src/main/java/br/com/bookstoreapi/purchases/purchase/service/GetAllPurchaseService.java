package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface GetAllPurchaseService {

    List<Purchase> findAll();
}
