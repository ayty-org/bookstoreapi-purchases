package br.com.bookstoreapi.purchases.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("clients-api")
public interface ClientRepository {

    @GetMapping("clients/{clientUuid}")
    ClientDTO getClient(@PathVariable UUID clientUuid);
}
