package br.com.bookstoreapi.purchases.book;


import java.util.List;
import java.util.UUID;

public interface GetBooksService {

    List<BookDTO> getBooksByUuid(List<UUID> ids);
}
