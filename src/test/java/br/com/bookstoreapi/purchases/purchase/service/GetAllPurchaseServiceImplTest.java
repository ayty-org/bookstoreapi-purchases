package com.bookstoreapi.bookstoreapi.purchase.service;

import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.bookstoreapi.bookstoreapi.purchase.Purchase;
import com.bookstoreapi.bookstoreapi.purchase.PurchaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllPurchaseServiceImplTest {

    private GetAllPurchaseServiceImpl getAllPurchaseService;
    @Mock
    private PurchaseRepository repository;


    @BeforeEach
    void setUp() {
        this.getAllPurchaseService = new GetAllPurchaseServiceImpl(repository);
    }

    @Test
    void findAllTest() {
        when(repository.findAll()).thenReturn(PurchaseBuilder.purchaseList());

        List<Purchase> purchases = getAllPurchaseService.findAll();

        assertThat(2, is(purchases.size()));

        assertThat(purchases.get(0).getId(), is(1L));
        assertThat(purchases.get(0).getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchases.get(0).getClient().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchases.get(0).getClient().getName(), is("Jenipapo"));
        assertThat(purchases.get(0).getPurchasedBooks().size(), is(3));
        assertThat(purchases.get(0).getAmount(), is(100.00));
        assertThat(purchases.get(0).getPurchaseDate(), is(new Date(14112020)));
        assertThat(purchases.get(0).getIsCompleted(), is(true));

        assertThat(purchases.get(1).getId(), is(2L));
        assertThat(purchases.get(1).getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(purchases.get(1).getClient().getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(purchases.get(1).getClient().getName(), is("Ana"));
        assertThat(purchases.get(1).getPurchasedBooks().size(), is(3));
        assertThat(purchases.get(1).getAmount(), is(200.0));
        assertThat(purchases.get(1).getPurchaseDate(), is(new Date(10102010)));
        assertThat(purchases.get(1).getIsCompleted(), is(false));
    }
}