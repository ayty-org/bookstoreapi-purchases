package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExistPurchaseByBookUuidImpl implements ExistPurchaseByBookUuid {

    private final PurchaseRepository repository;

    @Override
    public boolean existsByBookUuid(UUID uuid) {
        return repository.existsByBooksUuid(uuid);
    }
}
