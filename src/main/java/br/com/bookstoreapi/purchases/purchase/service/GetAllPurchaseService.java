package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

@FunctionalInterface
public interface GetAllPurchaseService {

    List<PurchaseResultDTO> findAll(Pageable pageable) throws EntityNotFoundException;
}
