package br.com.bookstoreapi.purchases.exception;

import java.util.UUID;

public class BookOutOfStockException extends Exception{


    public BookOutOfStockException(UUID id){
        super("Book with id "+ id+" is out of stock");
    }
}
