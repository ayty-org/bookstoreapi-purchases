package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRecieveDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class PurchaseBuilder {

    public static Purchase purchase1L() {
        return Purchase.builder()
                .id(1L)
                .uuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .client(ClientBuilder.clientJenipapo1())
                .purchasedBooks(BookBuilder.bookList())
                .amount(100.0)
                .purchaseDate(new Date(14112020))
                .isCompleted(true)
                .build();
    }

    public static Purchase purchase2L() {
        return Purchase.builder()
                .id(2L)
                .uuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"))
                .client(ClientBuilder.clientAna2())
                .purchasedBooks(BookBuilder.bookList())
                .amount(200.0)
                .purchaseDate(new Date(10102010))
                .isCompleted(false)
                .build();
    }

    public static PurchaseRecieveDTO purchaseRecieve() {
        return PurchaseRecieveDTO.builder()
                .client(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .purchasedBooks(List.of(
                        UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                        UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                ))
                .isCompleted(true)
                .build();
    }

    public static List<Purchase> purchaseList() {
        List<Purchase> purchases = new LinkedList<>();
        purchases.add(purchase1L());
        purchases.add(purchase2L());
        return purchases;
    }
}
