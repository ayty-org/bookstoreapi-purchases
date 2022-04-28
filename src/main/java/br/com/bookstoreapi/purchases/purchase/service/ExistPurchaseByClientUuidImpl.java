package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExistPurchaseByClientUuidImpl implements ExistPurchaseByClientUuid{

    private final PurchaseRepository repository;

    @Override
    public boolean existsByClientUuid(UUID id) {
        return repository.existsByClientUuid(id);
    }
}
