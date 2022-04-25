package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.BookDTO;
import com.bookstoreapi.bookstoreapi.client.ClientDTO;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseDTO {

    private UUID uuid;
    private ClientDTO client;
    private List<BookDTO> purchasedBooks;
    private Double amount;
    private Date purchaseDate;
    private Boolean isCompleted;

    public static PurchaseDTO from(Purchase purchase) {
        return PurchaseDTO.builder()
                .uuid(purchase.getUuid())
                .client(ClientDTO.from(purchase.getClient()))
                .purchasedBooks(BookDTO.fromAll(purchase.getPurchasedBooks()))
                .amount(purchase.getAmount())
                .purchaseDate(purchase.getPurchaseDate())
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

    public static List<PurchaseDTO> fromAll(List<Purchase> purchases) {
        return purchases.stream()
                .map(PurchaseDTO::from)
                .collect(Collectors.toList());
    }
}
