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

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SavePurchaseServiceImplTest {

    private SavePurchaseServiceImpl savePurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp(){
        this.savePurchaseService = new SavePurchaseServiceImpl(repository, bookRepository, clientRepository);
    }

    @Test
    void saveTest() throws Exception{
        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"), null))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());
        when(bookRepository.getBook(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(BookBuilder.book2L());
        when(bookRepository.getBook(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .thenReturn(BookBuilder.book3L());

        when(repository.save(any())).thenReturn(PurchaseBuilder.purchase1L());
        when(bookRepository.update(any(), any())).thenReturn(null);

        PurchaseResultDTO purchase = savePurchaseService.save(PurchaseBuilder.purchase1L(), null);

        verify(repository, times(1)).save(any());

        assertThat(purchase.getClientDTO().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchase.getBookDTOS().size(), is(3));
        assertThat(purchase.getAmount(), is(230.00));
        assertThat(purchase.getIsCompleted(), is(true));
    }

    @Test
    void saveWhenClientDontExistTest(){
        assertThrows(EntityNotFoundException.class,
                ()->savePurchaseService.save(PurchaseBuilder.purchase2L(), null));
        verify(repository, never()).save(any());
    }

    @Test
    void saveWhenBookDontExistTest(){
        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"), null))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());


        assertThrows(EntityNotFoundException.class,
                ()->savePurchaseService.save(PurchaseBuilder.purchase2L(), null));
        verify(repository, never()).save(any());
    }
}