package br.com.bookstoreapi.purchases.purchase.service;

import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.builders.BookBuilder;
import br.com.bookstoreapi.purchases.builders.ClientBuilder;
import br.com.bookstoreapi.purchases.builders.PurchaseBuilder;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseRepository;
import br.com.bookstoreapi.purchases.purchase.PurchaseResultDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class GetAllPurchaseServiceImplTest {

    private GetAllPurchaseServiceImpl getAllPurchaseService;
    @Mock
    private PurchaseRepository repository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;


    @BeforeEach
    void setUp() {
        this.getAllPurchaseService = new GetAllPurchaseServiceImpl(repository, bookRepository, clientRepository);
    }

    @Test
    void findAllTest() throws Exception{
        Pageable page = PageRequest.of(0,3);

        when(repository.findAll(page)).thenReturn(new PageImpl<>(PurchaseBuilder.purchaseList()));

        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(ClientBuilder.clientJenipapo1());
        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(ClientBuilder.clientAna2());

        when(bookRepository.getBook(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(BookBuilder.book1L());
        when(bookRepository.getBook(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(BookBuilder.book2L());
        when(bookRepository.getBook(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .thenReturn(BookBuilder.book3L());
        List<PurchaseResultDTO> purchases = getAllPurchaseService.findAll(page);

        assertThat(2, is(purchases.size()));

        assertThat(purchases.get(0).getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchases.get(0).getClientDTO().getUuid().toString(), is("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        assertThat(purchases.get(0).getClientDTO().getName(), is("Jenipapo"));
        assertThat(purchases.get(0).getBookDTOS().size(), is(3));
        assertThat(purchases.get(0).getAmount(), is(100.00));
        assertThat(purchases.get(0).getPurchaseDate(), is(new Date(14112020)));
        assertThat(purchases.get(0).getIsCompleted(), is(true));

        assertThat(purchases.get(1).getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(purchases.get(1).getClientDTO().getUuid().toString(), is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        assertThat(purchases.get(1).getClientDTO().getName(), is("Ana"));
        assertThat(purchases.get(1).getBookDTOS().size(), is(3));
        assertThat(purchases.get(1).getAmount(), is(200.0));
        assertThat(purchases.get(1).getPurchaseDate(), is(new Date(10102010)));
        assertThat(purchases.get(1).getIsCompleted(), is(false));
    }
}