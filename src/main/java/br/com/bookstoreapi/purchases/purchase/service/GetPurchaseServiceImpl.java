package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class GetPurchaseServiceImpl extends GetFieldsByUuidService implements GetPurchaseService{

    private final PurchaseRepository purchaseRepository;


    public GetPurchaseServiceImpl(@Autowired PurchaseRepository purchaseRepository,
                                  BookRepository bookRepository, ClientRepository clientRepository) {
        super(bookRepository, clientRepository);
        this.purchaseRepository = purchaseRepository;
    }


    public PurchaseResultDTO getByUuid(UUID uuid) throws EntityNotFoundException {
        Purchase purchase = this.purchaseRepository.findByUuid(uuid)
                .orElseThrow(()-> new EntityNotFoundException(uuid, Purchase.class.getSimpleName()));

        PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
        purchaseResultDTO.setClientDTO(getClientByUuid(purchase.getClientUuid()));
        purchaseResultDTO.setBookDTOS(getBooksByUuid(purchase.getBooksUuid()));
        return purchaseResultDTO;
    }
}
