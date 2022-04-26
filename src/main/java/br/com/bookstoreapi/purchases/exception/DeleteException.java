package br.com.bookstoreapi.purchases.exception;

import java.util.UUID;

public class DeleteException extends Exception{

    public DeleteException(UUID id, String className){
        super(className+ " with id " +id+" cannot be deleted because it is in one or more purchases");
    }
}
