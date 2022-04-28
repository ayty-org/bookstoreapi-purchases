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
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;


    @Override
    public PurchaseResultDTO update(UUID uuid, Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        Optional<Purchase> purchaseSaved = purchaseRepository.findByUuid(uuid);
        if (purchaseSaved.isPresent()) {
            List<BookDTO> booksFromOld = getBooksByUuid(purchaseSaved.get().getBooksUuid());
            List<BookDTO> books = getBooksByUuid(purchase.getBooksUuid());
            PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
            purchaseResultDTO.setClientDTO(this.clientRepository.getClient(purchase.getClientUuid()));
            purchaseResultDTO.setBookDTOS(books);
            purchaseResultDTO.setAmount(getAmountToPay(books));
            purchase.setId(purchaseSaved.get().getId());
            purchase.setUuid(uuid);
            purchaseRepository.save(purchase);
            updateBooksStock(books, booksFromOld);
            return purchaseResultDTO;
        }
        throw new EntityNotFoundException(uuid, Purchase.class.getSimpleName());
    }

    private void updateBooksStock(List<BookDTO> updated, List<BookDTO> old) throws BookOutOfStockException {
        this.updateBooksStockToUp(old);
        this.updateBooksStockToDown(updated);
    }

    private List<BookDTO> getBooksByUuid(List<UUID> books) throws EntityNotFoundException {
        List<BookDTO> bookList = new ArrayList<>();
        for (UUID uuid : books) {
            BookDTO bookDTO = bookRepository.getBook(uuid);
            if(bookDTO != null){
                bookList.add(bookDTO);
            }else{
                throw new EntityNotFoundException(uuid, "Book");
            }
        }
        return bookList;
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

    private void updateBooksStockToUp(List<BookDTO> books) {
        for (BookDTO book : books) {
            book.setQuantityInStock(book.getQuantityInStock() + 1);
            this.bookRepository.update(book.getUuid(), BookResultDTO.from(book));
        }
    }
}
