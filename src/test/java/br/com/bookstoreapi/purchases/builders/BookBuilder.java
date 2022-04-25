package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.book.Book;
import com.bookstoreapi.bookstoreapi.book.BookRecieveDTO;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BookBuilder {

    public static Book book1L(){
        return Book.builder()
                .id(1L)
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

    public static Book book2L(){
        return Book.builder()
                .id(2L)
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

    public static Book book3L(){
        return Book.builder()
                .id(3L)
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

    public static BookRecieveDTO bookInvalid(){
        return BookRecieveDTO.builder()
                .categories(List.of(1L,2L,3L))
                .title("")
                .synopsis("Entenda lógica de programação")
                .isbn("9788533302273")
                .publicationYear(new Date(30042000))
                .price(100.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }

    public static List<Book> bookList(){
        List<Book> books = new LinkedList<>();
        books.add(book1L());
        books.add(book2L());
        books.add(book3L());
        return books;
    }

    public static BookRecieveDTO book1LBookRecieve(){
        return BookRecieveDTO.builder()
                .categories(List.of(1L,2L,3L))
                .title("JavaScript")
                .synopsis("Aprenda JavaScript")
                .isbn("9788533302273")
                .publicationYear(new Date(14032001))
                .price(50.00)
                .quantityInStock(23)
                .authorName("JN Papo")
                .build();
    }
}
