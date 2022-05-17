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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdatePurchaseServiceImpl extends UpdateBookStockService implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;


    public UpdatePurchaseServiceImpl(@Autowired PurchaseRepository purchaseRepository,
                                     BookRepository bookRepository, ClientRepository clientRepository) {
        super(bookRepository, clientRepository);
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public PurchaseResultDTO update(UUID uuid, Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        Optional<Purchase> purchaseSaved = purchaseRepository.findByUuid(uuid);
        if (purchaseSaved.isPresent()) {
            List<BookDTO> booksFromOld = getBooksByUuid(purchaseSaved.get().getBooksUuid());
            List<BookDTO> books = getBooksByUuid(purchase.getBooksUuid());
            purchase.setId(purchaseSaved.get().getId());
            purchase.setUuid(uuid);
            purchase.setPurchaseDate(purchaseSaved.get().getPurchaseDate());
            PurchaseResultDTO purchaseResultDTO = PurchaseResultDTO.from(purchase);
            purchaseResultDTO.setClientDTO(getClientByUuid(purchase.getClientUuid()));
            purchaseResultDTO.setBookDTOS(books);
            purchaseResultDTO.setAmount(getAmountToPay(books));
            purchaseResultDTO.setUuid(uuid);
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

    private double getAmountToPay(List<BookDTO> books) {
        double amount = 0.0;
        for (BookDTO book : books) {
            amount += book.getPrice();
        }
        return amount;
    }
}
