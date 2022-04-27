package br.com.bookstoreapi.purchases.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetClientServiceImpl implements GetClientService{

    private final ClientRepository clientRepository;

    @Override
    public ClientDTO getClientByUuid(UUID id) {
        return clientRepository.getClient(id);
    }
}
