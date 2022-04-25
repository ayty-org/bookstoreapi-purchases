package com.bookstoreapi.bookstoreapi.exception;

public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(Long id){
        super("Category with id "+ id+ " not found");
    }

}
