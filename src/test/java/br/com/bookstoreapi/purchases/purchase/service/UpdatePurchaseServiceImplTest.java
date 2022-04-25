package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.book.BookRepository;
import com.bookstoreapi.bookstoreapi.builders.BookBuilder;
import com.bookstoreapi.bookstoreapi.builders.ClientBuilder;
import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.bookstoreapi.bookstoreapi.client.ClientRepository;
import com.bookstoreapi.bookstoreapi.exception.EntityNotFoundException;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
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
        when(clientRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        when(bookRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));
        when(bookRepository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(BookBuilder.book2L()));
        when(bookRepository.findByUuid(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .thenReturn(Optional.of(BookBuilder.book3L()));
        when(repository.save(any())).thenReturn(PurchaseBuilder.purchase1L());
        when(bookRepository.saveAll(any())).thenReturn(BookBuilder.bookList());
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        Purchase purchase = updatePurchaseService.update(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"),
                PurchaseBuilder.purchase1L());

        verify(repository, times(1)).save(any());

        assertThat(1L, is(purchase.getId()));
        assertThat("12d51c0a-a843-46fc-8447-5fda559ec69b", is(purchase.getUuid().toString()));
        assertThat("12d51c0a-a843-46fc-8447-5fda559ec69b", is(purchase.getClient().getUuid().toString()));
        assertThat("Jenipapo", is(purchase.getClient().getName()));
        assertThat(3, is(purchase.getPurchasedBooks().size()));
        assertThat(100.0, is(purchase.getAmount()));
        assertThat(new Date(14112020), is(purchase.getPurchaseDate()));
        assertThat(true, is(purchase.getIsCompleted()));
    }

    @Test
    void updateWhenIdDontExistTest(){
        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());

    }

    @Test
    void updateWhenClientDontExistTest() {
        when(clientRepository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.empty());
        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase2L()));

        assertThrows(EntityNotFoundException.class,
                () -> updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());
    }

    @Test
    void updateWhenBookDontExistTest(){
        when(clientRepository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(ClientBuilder.clientJenipapo1()));

        when(bookRepository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(BookBuilder.book1L()));
        when(bookRepository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.empty());

        when(repository.findByUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase2L()));

        assertThrows(EntityNotFoundException.class,
                ()->updatePurchaseService.update(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"),
                        PurchaseBuilder.purchase2L()));
        verify(repository, never()).save(any());
    }
}