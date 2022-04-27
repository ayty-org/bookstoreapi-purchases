package br.com.bookstoreapi.purchases.client;

import java.util.UUID;

public interface GetClientService {

    ClientDTO getClientByUuid(UUID id);
}
