package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.builders.BookBuilder;
import br.com.bookstoreapi.purchases.builders.ClientBuilder;
import br.com.bookstoreapi.purchases.builders.PurchaseBuilder;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.Purchase;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.hamcrest.Matchers;
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
class UpdatePurchaseServiceImplTest {

    private UpdatePurchaseServiceImpl updatePurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp(){
      this.updatePurchaseService = new UpdatePurchaseServiceImpl(repository, bookRepository, clientRepository);
    }

    @Test
    void updateTest() throws Exception{
        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());
        when(bookRepository.getBook(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(BookBuilder.book2L());
        when(bookRepository.getBook(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .thenReturn(BookBuilder.book3L());
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        when(repository.save(any())).thenReturn(PurchaseBuilder.purchase1L());
        when(bookRepository.update(any(), any())).thenReturn(null);

        PurchaseResultDTO purchase = updatePurchaseService.update(
                UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                PurchaseBuilder.purchase1L(), null);

        verify(repository, times(1)).save(any());

        assertThat(purchase.getClientDTO().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getClientDTO().getName(), is("Jenipapo"));
        assertThat(purchase.getBookDTOS().size(), is(3));
        assertThat(purchase.getAmount(), is(230.0));
        assertThat(purchase.getIsCompleted(), is(true));
    }

    @Test
    void updateWhenIdDontExistTest(){
        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L(), null));
        verify(repository, never()).save(any());

    }

    @Test
    void updateWhenClientDontExistTest() {
//        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
//                .thenReturn(Optional.empty());
        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase2L()));

        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L(), null));
        verify(repository, never()).save(any());
    }

    @Test
    void updateWhenBookDontExistTest(){
        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"), null))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());
//        when(bookRepository.getBook(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
//                .thenReturn(Optional.empty());

        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase2L()));

        assertThrows(EntityNotFoundException.class,
                ()->updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L(), null));
        verify(repository, never()).save(any());
    }
}