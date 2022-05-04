package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SavePurchaseServiceImpl extends UpdateBookStockService implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;


    public SavePurchaseServiceImpl(@Autowired PurchaseRepository purchaseRepository,
                                   BookRepository bookRepository, ClientRepository clientRepository) {
        super(bookRepository, clientRepository);
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public PurchaseResultDTO save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        List<BookDTO> books = getBooksByUuid(purchase.getBooksUuid());

        PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
        purchaseResultDTO.setClientDTO(getClientByUuid(purchase.getClientUuid()));
        purchaseResultDTO.setBookDTOS(books);
        purchaseResultDTO.setAmount(getAmountToPay(books));
        purchaseResultDTO.setPurchaseDate(new Date());
        purchaseResultDTO.setUuid(UUID.randomUUID());
        purchase.setAmount(purchaseResultDTO.getAmount());
        purchase.setPurchaseDate(purchaseResultDTO.getPurchaseDate());
        purchase.setUuid(purchaseResultDTO.getUuid());
        this.purchaseRepository.save(purchase);
        updateBooksStockToDown(books);
        return purchaseResultDTO;
    }

    private double getAmountToPay(List<BookDTO> books) {
        double amount = 0.0;
        for (BookDTO book : books) {
            amount += book.getPrice();
        }
        return amount;
    }
}
