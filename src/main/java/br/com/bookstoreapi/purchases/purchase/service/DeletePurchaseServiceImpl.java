package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService {

    private final PurchaseRepository purchaseRepository;


    @Override
    public void delete(UUID id) throws EntityNotFoundException {
        Optional<Purchase> purchaseOptional = purchaseRepository.findByUuid(id);
        if(purchaseOptional.isPresent()){
            purchaseRepository.delete(purchaseOptional.get());
        }else{
            throw new EntityNotFoundException(id, Purchase.class.getSimpleName());
        }
    }

}
