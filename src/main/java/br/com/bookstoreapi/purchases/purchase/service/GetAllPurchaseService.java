package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface GetAllPurchaseService {

    List<Purchase> findAll();
}
