package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.book.BookResultDTO;
import br.com.bookstoreapi.purchases.client.ClientDTO;
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

    private ClientDTO getClientByUuid(UUID id) throws EntityNotFoundException{
        ClientDTO clientDTO = clientRepository.getClient(id);
        if(clientDTO != null){
            return clientDTO;
        }
        throw new EntityNotFoundException(id, "Client");
    }

    private List<BookDTO> getBooksByUuid(List<UUID> books) throws EntityNotFoundException {
        List<BookDTO> bookList = new ArrayList<>();
        for (UUID uuid : books) {
            BookDTO bookDTO = bookRepository.getBook(uuid);
            if (bookDTO != null) {
                bookList.add(bookDTO);
            } else {
                throw new EntityNotFoundException(uuid, "Book");
            }
        }
        return bookList;
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
