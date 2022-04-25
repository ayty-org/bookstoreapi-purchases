package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.client.Client;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.uuid.UUIDGenerator")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "uuid")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="purchases_purchased_books",
            joinColumns = {
                    @JoinColumn(name = "purchase_id", referencedColumnName = "uuid")
            },
            inverseJoinColumns = {
                    @JoinColumn(table = "books",name = "book_id", referencedColumnName = "uuid")
            }
    )
    private List<Book> purchasedBooks;

    private Double amount;
    private Date purchaseDate;
    private Boolean isCompleted;

}
