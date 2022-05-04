package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.builders.BookBuilder;
import br.com.bookstoreapi.purchases.builders.ClientBuilder;
import br.com.bookstoreapi.purchases.builders.PurchaseBuilder;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class GetPurchaseServiceImplTest {

    private GetPurchaseServiceImpl getPurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp() {
        this.getPurchaseService = new GetPurchaseServiceImpl(repository, bookRepository, clientRepository);
    }

    @Test
    void GetByIdWhenIdExistTest() throws Exception {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());
        when(bookRepository.getBook(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(BookBuilder.book2L());
        when(bookRepository.getBook(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .thenReturn(BookBuilder.book3L());

        PurchaseResultDTO purchase = getPurchaseService.getByUuid(
                UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));

        verify(repository, times(1)).findByUuid(any());
        assertThat(purchase.getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getClientDTO().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getClientDTO().getName(), is("Jenipapo"));
        assertThat(purchase.getBookDTOS().size(), is(3));
        assertThat(purchase.getAmount(), is(100.00));
        assertThat(purchase.getPurchaseDate(), is(new Date(14112020)));
        assertThat(purchase.getIsCompleted(), is(true));
    }

    @Test
    void GetByIdWhenIdDontExistTest() {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> getPurchaseService.getByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")));
    }
}