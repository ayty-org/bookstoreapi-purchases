package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.builders.PurchaseBuilder;
import br.com.bookstoreapi.purchases.exception.EntityNotFoundException;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class
DeletePurchaseServiceImplTest {

    private DeletePurchaseServiceImpl deletePurchaseService;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp() {
        this.deletePurchaseService = new DeletePurchaseServiceImpl(repository);
    }

    @Test
    void deleteWhenIdExistTest() throws Exception {
        when(repository.findByUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(Optional.of(PurchaseBuilder.purchase1L()));

        deletePurchaseService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));

        verify(repository, times(1)).findByUuid(any());
        verify(repository, times(1)).delete(any());
    }

    @Test
    void deleteWhenIdDontExist() {
        assertThrows(EntityNotFoundException.class,
                () -> deletePurchaseService.delete(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")));
        verify(repository, never()).delete(any());
    }
}