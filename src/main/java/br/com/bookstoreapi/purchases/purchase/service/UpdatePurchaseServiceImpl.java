package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.exception.BookOutOfStockException;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService {

    private final PurchaseRepository purchaseRepository;
//    private final BookRepository bookRepository;
//    private final ClientRepository clientRepository;


    @Override
    public Purchase update(UUID uuid, Purchase purchase) throws EntityNotFoundException, BookOutOfStockException {
        Optional<Purchase> purchaseSaved = purchaseRepository.findByUuid(uuid);
        if(purchaseSaved.isPresent()){
//            List<Book> booksFromOld = getBooksByUuid(purchaseSaved.get().getPurchasedBooks());
//            List<Book> books = getBooksByUuid(purchase.getPurchasedBooks());
//            purchase.setPurchasedBooks(books);
//            purchase.setClient(getClientByUuid(purchase.getClient().getUuid()));
//            purchase.setAmount(getAmountToPay(purchase.getPurchasedBooks()));
            purchase.setId(purchaseSaved.get().getId());
            purchase.setUuid(uuid);
            purchase.setPurchaseDate(purchaseSaved.get().getPurchaseDate());
//            updateBooksStock(books, booksFromOld);
            purchase.setPurchaseDate(new Date());
            return purchaseRepository.save(purchase);
        }
        throw new EntityNotFoundException(uuid, Purchase.class.getSimpleName());
    }

//    private void updateBooksStock(List<Book> updated, List<Book> old) throws BookOutOfStockException{
//          this.updateBooksStockToUp(old);
//          this.updateBooksStockToDown(updated);
//    }
//
//    private List<Book> getBooksByUuid(List<Book> books) throws EntityNotFoundException{
//        List<Book> bookList= new ArrayList<>();
//        for(Book book: books){
//            bookList.add(bookRepository.findByUuid(book.getUuid()).orElseThrow(
//                    ()-> new EntityNotFoundException(book.getUuid(), Book.class.getSimpleName())));
//        }
//        return bookList;
//    }
//
//    private Client getClientByUuid(UUID id) throws EntityNotFoundException {
//        return clientRepository.findByUuid(id).orElseThrow(
//                () -> new EntityNotFoundException(id, Client.class.getSimpleName()));
//    }
//
//    private double getAmountToPay(List<Book> books){
//        double amount = 0.0;
//        for(Book book: books){
//            amount += book.getPrice();
//        }
//        return amount;
//    }
//
//    private void updateBooksStockToDown(List<Book> books) throws BookOutOfStockException{
//        List<Book> booksToUpdate = new ArrayList<>();
//        for(Book book: books){
//            if(book.getQuantityInStock() > 0){
//                book.setQuantityInStock(book.getQuantityInStock()-1);
//                booksToUpdate.add(book);
//            }else{
//                throw new BookOutOfStockException(book.getUuid());
//            }
//        }
//        bookRepository.saveAll(booksToUpdate);
//    }
//
//    private void updateBooksStockToUp(List<Book> books){
//        List<Book> booksToUpdate = new ArrayList<>();
//        for(Book book: books){
//            book.setQuantityInStock(book.getQuantityInStock()+1);
//            booksToUpdate.add(book);
//        }
//        bookRepository.saveAll(booksToUpdate);
//    }
}
