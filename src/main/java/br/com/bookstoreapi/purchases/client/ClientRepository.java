package br.com.bookstoreapi.purchases.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.UUID;

@FeignClient("clients-api")
public interface ClientRepository {

    @GetMapping("/v1/clients/private/{clientUuid}")
    ClientDTO getClient(@PathVariable UUID clientUuid, @RequestHeader("Authorization") String bearerToken);
}
