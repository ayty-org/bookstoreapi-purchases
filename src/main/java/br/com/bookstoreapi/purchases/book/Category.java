package br.com.bookstoreapi.purchases.book;

import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    private Long id;
    private String name;
}
