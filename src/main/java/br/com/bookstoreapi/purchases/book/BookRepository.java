package br.com.bookstoreapi.purchases.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient("books-api")
public interface BookRepository {

    @GetMapping("/books/{bookUuid}")
    BookDTO getBook(@PathVariable UUID bookUuid);

    @PutMapping("/books/{bookUuid}")
    BookDTO update(@PathVariable UUID bookUuid, BookResultDTO bookResultDTO);
}
