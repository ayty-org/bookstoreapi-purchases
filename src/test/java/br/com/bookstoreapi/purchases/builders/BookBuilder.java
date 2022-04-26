package br.com.bookstoreapi.purchases.builders;

import br.com.bookstoreapi.purchases.book.BookDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BookBuilder {

    public static BookDTO book1L(){
        return BookDTO.builder()
                .uuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .categories(CategoryBuilder.categoryList())
                .title("JavaScript")
                .synopsis("Aprenda JavaScript")
                .isbn("9788533302273")
                .publicationYear(new Date(14032001))
                .price(50.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }

    public static BookDTO book2L(){
        return BookDTO.builder()
                .uuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"))
                .categories(CategoryBuilder.categoryList())
                .title("Angular JS")
                .synopsis("Aprenda a primeira versão do Angular")
                .isbn("9788533302273")
                .publicationYear(new Date(15042000))
                .price(80.00)
                .quantityInStock(4)
                .authorName("Gu Gou")
                .build();
    }

    public static BookDTO book3L(){
        return BookDTO.builder()
                .uuid(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61"))
                .categories(CategoryBuilder.categoryList())
                .title("Algoritmos")
                .synopsis("Entenda lógica de programação")
                .isbn("9788533302273")
                .publicationYear(new Date(30042000))
                .price(100.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }

    public static List<BookDTO> bookList(){
        List<BookDTO> books = new LinkedList<>();
        books.add(book1L());
        books.add(book2L());
        books.add(book3L());
        return books;
    }

}
