package br.com.bookstoreapi.purchases.exception;

import java.util.UUID;

public class EntityNotFoundException extends Exception{

    public EntityNotFoundException(UUID id, String className){
        super(className+" with id "+id+" not found");
    }
}
