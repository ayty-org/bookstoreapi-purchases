package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookDTO;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.client.ClientDTO;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetFieldsByUuidService {

    protected final BookRepository bookRepository;
    protected final ClientRepository clientRepository;



    protected ClientDTO getClientByUuid(UUID id, String bearerToken) throws EntityNotFoundException {
        ClientDTO clientDTO = clientRepository.getClient(id, bearerToken);
        if(clientDTO != null){
            return clientDTO;
        }
        throw new EntityNotFoundException(id, "Client");
    }

    protected List<BookDTO> getBooksByUuid(List<UUID> books) throws EntityNotFoundException {
        List<BookDTO> bookList = new ArrayList<>();
        for (UUID uuid : books) {
            BookDTO bookDTO = bookRepository.getBook(uuid);
            if (bookDTO != null) {
                bookList.add(bookDTO);
            } else {
                throw new EntityNotFoundException(uuid, "Book");
            }
        }
        return bookList;
    }
}
