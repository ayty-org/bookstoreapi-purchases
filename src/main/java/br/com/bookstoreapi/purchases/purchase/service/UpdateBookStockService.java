package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.book.BookResultDTO;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateBookStockService extends GetFieldsByUuidService{


    public UpdateBookStockService(BookRepository bookRepository, ClientRepository clientRepository) {
        super(bookRepository, clientRepository);
    }



    protected void updateBooksStockToDown(List<BookDTO> books) throws BookOutOfStockException {
        for (BookDTO book : books) {
            if (book.getQuantityInStock() > 0) {
                book.setQuantityInStock(book.getQuantityInStock() - 1);
                this.bookRepository.update(book.getUuid(), BookResultDTO.from(book));
            } else {
                throw new BookOutOfStockException(book.getUuid());
            }
        }
    }

    protected void updateBooksStockToUp(List<BookDTO> books) {
        for (BookDTO book : books) {
            book.setQuantityInStock(book.getQuantityInStock() + 1);
            this.bookRepository.update(book.getUuid(), BookResultDTO.from(book));
        }
    }
}
