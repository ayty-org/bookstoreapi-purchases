package br.com.bookstoreapi.purchases.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.UUID;

@FeignClient(name = "books", url = "http://localhost:8082/books")
public interface BookRepository {

    @GetMapping("/{bookUuid}")
    BookDTO getBook(@PathVariable UUID bookUuid);

    @PutMapping("/{bookUuid}")
    void update(@PathVariable UUID bookUuid, BookResultDTO bookResultDTO);
}
