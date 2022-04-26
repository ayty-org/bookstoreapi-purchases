package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllPurchaseServiceImpl implements GetAllPurchaseService{

    private final PurchaseRepository purchaseRepository;


    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
}
