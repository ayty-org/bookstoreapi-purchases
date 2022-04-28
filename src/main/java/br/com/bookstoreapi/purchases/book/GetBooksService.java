package br.com.bookstoreapi.purchases.book;


import java.util.List;
import java.util.UUID;

@FunctionalInterface
public interface GetBooksService {

    List<BookDTO> getBooksByUuid(List<UUID> ids);
}
