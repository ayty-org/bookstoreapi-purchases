package br.com.bookstoreapi.purchases.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "clients", url = "http://localhost:8081/clients")
public interface ClientRepository {

    @GetMapping("/{clientUuid}")
    ClientDTO getClient(@PathVariable UUID clientUuid);
}
