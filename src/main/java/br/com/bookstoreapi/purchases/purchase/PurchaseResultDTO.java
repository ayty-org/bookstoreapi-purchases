package br.com.bookstoreapi.purchases.purchase;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.client.ClientDTO;
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
public class PurchaseResultDTO {

    private UUID uuid;
    private ClientDTO clientDTO;
    private List<BookDTO> bookDTOS;
    private Double amount;
    private Date purchaseDate;
    private Boolean isCompleted;

    public static PurchaseResultDTO from(Purchase purchase) {
        return PurchaseResultDTO.builder()
                .uuid(purchase.getUuid())
                //.client_uuid(ClientDTO.from(purchase.getClient()))
                //.books_uuid(BookDTO.fromAll(purchase.getPurchasedBooks()))
                .amount(purchase.getAmount())
                .purchaseDate(purchase.getPurchaseDate())
                .isCompleted(purchase.getIsCompleted())
                .build();
    }

    public static List<PurchaseResultDTO> fromAll(List<Purchase> purchases) {
        return purchases.stream()
                .map(PurchaseResultDTO::from)
                .collect(Collectors.toList());
    }
}
