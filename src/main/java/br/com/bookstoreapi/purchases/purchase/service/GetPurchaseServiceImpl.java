package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
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
