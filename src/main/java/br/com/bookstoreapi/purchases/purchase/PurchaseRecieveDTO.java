package br.com.bookstoreapi.purchases.purchase;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRecieveDTO {

    @NotNull(message = "purchase client cannot be null")
    private UUID clientUuid;
    @NotNull(message = "a purchase must have at least one book")
    private List<UUID> booksUuid;
    @NotNull(message = "purchase status cannot be null")
    private Boolean isCompleted;


    public static Purchase to(PurchaseRecieveDTO purchase) {
        return Purchase.builder()
                .clientUuid(purchase.getClientUuid())
                .booksUuid(purchase.getBooksUuid())
                .amount(0.0)
                .isCompleted(purchase.getIsCompleted())
                .build();
    }
}
