package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecieveDTO {

    @NotNull(message = "purchase client cannot be null")
    private UUID client;
    @NotNull(message = "a purchase must have at least one book")
    private List<UUID> purchasedBooks;
    @NotNull(message = "purchase status cannot be null")
    private Boolean isCompleted;


    public static Purchase to(PurchaseRecieveDTO purchase) {
        Client client = Client.builder().uuid(purchase.getClient()).build();
        List<Book> books = new ArrayList<>();
        for(UUID id: purchase.getPurchasedBooks()){
            books.add(Book.builder().uuid(id).build());
        }
        return Purchase.builder()
                .client(client)
                //provavelmente vai dar nullpoint por conta do uuid aqui
                .purchasedBooks(books)
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

}
