package br.com.bookstoreapi.purchases.client;

import java.util.UUID;

@FunctionalInterface
public interface GetClientService {

    ClientDTO getClientByUuid(UUID id);
}
