package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TotalElementsService {

    private final PurchaseRepository purchaseRepository;


    public Integer getTotalElements(){
        return this.purchaseRepository.findAll().size();
    }

}
