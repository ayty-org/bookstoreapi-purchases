package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    private final PurchaseRepository purchaseRepository;


    public Purchase getByUuid(UUID uuid) throws EntityNotFoundException {
        return this.purchaseRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Purchase.class.getSimpleName()));
    }
}
