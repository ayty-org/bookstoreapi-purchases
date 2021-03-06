package br.com.bookstoreapi.purchases.purchase;

import lombok.*;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Table(name = "purchases")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Purchase implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    private UUID clientUuid;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<UUID> booksUuid;

    private Double amount;
    private Date purchaseDate;
    private Boolean isCompleted;

}
