package br.com.bookstoreapi.purchases.book;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResultDTO implements Serializable {

    private String title;
    private String synopsis;
    private String isbn;
    private Date publicationYear;
    private Double price;
    private Integer quantityInStock;
    private String authorName;
    private List<Long> categories;

    public static BookResultDTO from(BookDTO bookDTO){
        return BookResultDTO.builder()
                .title(bookDTO.getTitle())
                .synopsis(bookDTO.getSynopsis())
                .isbn(bookDTO.getIsbn())
                .publicationYear(bookDTO.getPublicationYear())
                .price(bookDTO.getPrice())
                .quantityInStock(bookDTO.getQuantityInStock())
                .authorName(bookDTO.getAuthorName())
                .categories(bookDTO.getCategories().stream().map(Category::getId).collect(Collectors.toList()))
                .build();
    }
}
