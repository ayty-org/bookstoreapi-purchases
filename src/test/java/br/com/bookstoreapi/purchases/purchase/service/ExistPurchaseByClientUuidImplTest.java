package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ExistPurchaseByClientUuidImplTest {

    private ExistPurchaseByClientService existPurchaseByClientUuid;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp(){
        this.existPurchaseByClientUuid = new ExistPurchaseByClientServiceImpl(repository);
    }


    @Test
    void existsByBookUuidTest(){
        when(repository.existsByClientUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(true);

        assertThat(existPurchaseByClientUuid.existsByClientUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                , is(true));
        assertThat(existPurchaseByClientUuid.existsByClientUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"))
                , is(false));

    }
}