package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.book.BookResultDTO;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public PurchaseResultDTO save(Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        List<BookDTO> books = new LinkedList<>();
        for (UUID id : purchase.getBooksUuid()) {
            books.add(this.bookRepository.getBook(id));
        }
        PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
        purchaseResultDTO.setClientDTO(this.clientRepository.getClient(purchase.getClientUuid()));
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

    private void updateBooksStockToDown(List<BookDTO> books) throws BookOutOfStockException {
        for (BookDTO book : books) {
            if (book.getQuantityInStock() > 0) {
                book.setQuantityInStock(book.getQuantityInStock() - 1);
                this.bookRepository.update(book.getUuid(), BookResultDTO.from(book));
            } else {
                throw new BookOutOfStockException(book.getUuid());
            }
        }
    }
}
