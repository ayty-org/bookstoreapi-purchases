package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class GetAllPurchaseServiceImpl extends GetFieldsByUuidService implements GetAllPurchaseService{

    private final PurchaseRepository purchaseRepository;

    public GetAllPurchaseServiceImpl(@Autowired PurchaseRepository purchaseRepository,
                                     BookRepository bookRepository, ClientRepository clientRepository) {
        super(bookRepository, clientRepository);
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public List<PurchaseResultDTO> findAll(Pageable pageable) throws EntityNotFoundException {
        List<PurchaseResultDTO> purchaseResultDTOS = new LinkedList<>();
        for(Purchase purchase: purchaseRepository.findAll(pageable).toList()){
            PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
            purchaseResultDTO.setClientDTO(this.getClientByUuid(purchase.getClientUuid()));
            purchaseResultDTO.setBookDTOS(this.getBooksByUuid(purchase.getBooksUuid()));
            purchaseResultDTOS.add(purchaseResultDTO);
        }
        return purchaseResultDTOS;
    }
}
