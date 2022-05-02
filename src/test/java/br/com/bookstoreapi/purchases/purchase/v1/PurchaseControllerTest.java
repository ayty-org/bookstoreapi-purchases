package br.com.bookstoreapi.purchases.purchase.v1;

import br.com.bookstoreapi.purchases.BookstorePurchasesApplicationTests;
import br.com.bookstoreapi.purchases.book.BookRepository;
import br.com.bookstoreapi.purchases.book.GetBooksService;
import br.com.bookstoreapi.purchases.book.GetBooksServiceImpl;
import br.com.bookstoreapi.purchases.builders.BookBuilder;
import br.com.bookstoreapi.purchases.builders.ClientBuilder;
import br.com.bookstoreapi.purchases.builders.PurchaseBuilder;
import br.com.bookstoreapi.purchases.client.ClientRepository;
import br.com.bookstoreapi.purchases.client.GetClientService;
import br.com.bookstoreapi.purchases.client.GetClientServiceImpl;
import br.com.bookstoreapi.purchases.purchase.PurchaseRecieveDTO;
import br.com.bookstoreapi.purchases.purchase.service.*;
import br.com.bookstoreapi.purchases.purchase.v1.PurchaseController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
public class PurchaseControllerTest extends BookstorePurchasesApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private GetAllPurchaseService getAllPurchaseService;
    @Autowired
    private GetPurchaseService getPurchaseService;
    @Autowired
    private SavePurchaseService savePurchaseService;
    @Autowired
    private UpdatePurchaseService updatePurchaseService;
    @Autowired
    private DeletePurchaseService deletePurchaseService;
    @Autowired
    private ExistPurchaseByClientUuid existPurchaseByClientUuid;
    @Autowired
    private ExistPurchaseByBookUuid existPurchaseByBookUuid;

    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private ClientRepository clientRepository;


    private final String url = "/purchases";
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        GetBooksService getBooksService = new GetBooksServiceImpl(bookRepository);
        GetClientService getClientService = new GetClientServiceImpl(clientRepository);

        PurchaseController purchaseController = new PurchaseController(getAllPurchaseService, getPurchaseService, savePurchaseService
                , updatePurchaseService, deletePurchaseService, getBooksService, getClientService, existPurchaseByClientUuid, existPurchaseByBookUuid);

        this.mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
    }

    @Test
    void saveTest() throws Exception {
        String json = mapper.writeValueAsString(PurchaseBuilder.purchaseRecieve());


        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(bookRepository.getBook(any()))
                .thenReturn(BookBuilder.book1L());
        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientDTO.name", is("Jenipapo")))
                .andExpect(jsonPath("$.clientDTO.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.amount", is(150.0)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }


    @Test
    void saveWhenPurchaseIsInvalid() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setClientUuid(null);
        String json = mapper.writeValueAsString(p);

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    void saveWhenClientDontExist() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setClientUuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ecabc"));
        String json = mapper.writeValueAsString(p);

        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(null);
        when(bookRepository.getBook(any())).thenReturn(BookBuilder.book1L());

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(post(this.url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id 12d51c0a-a843-46fc-8447-5fda559ecabc not found");
    }

    @Test
    void saveWhenBookDontExist() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setBooksUuid(List.of(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ecabc")));
        String json = mapper.writeValueAsString(p);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(post(this.url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 12d51c0a-a843-46fc-8447-5fda559ecabc not found");
    }

    @Test
    void getAllTest() throws Exception {
        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(ClientBuilder.clientJenipapo1());

        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(ClientBuilder.clientAna2());

        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].clientDTO.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].clientDTO.name", is("Jenipapo")))
                .andExpect(jsonPath("$[0].amount", is(100.00)))
                .andExpect(jsonPath("$[0].isCompleted", is(true)))
                .andExpect(jsonPath("$[1].uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].clientDTO.uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].clientDTO.name", is("Ana")))
                .andExpect(jsonPath("$[1].amount", is(200.00)))
                .andExpect(jsonPath("$[1].isCompleted", is(false)));
    }

    @Test
    void getTest() throws Exception {
        when(clientRepository.getClient(any())).thenReturn(ClientBuilder.clientJenipapo1());

        mockMvc.perform(get(url + "/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.clientDTO.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.clientDTO.name", is("Jenipapo")))
                .andExpect(jsonPath("$.amount", is(100.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }

    @Test
    void getWhenIdDontExist() {
        Assertions.assertThatThrownBy(() -> mockMvc.perform(get(url + "/12d51c0a-a843-46fc-8447-05fda559e8a5"))
                        .andExpect(status().isNotFound()))
                .hasMessageContainingAll("Purchase with id 12d51c0a-a843-46fc-8447-05fda559e8a5 not found");
    }

    @Test
    void updateTest() throws Exception {
        when(clientRepository.getClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .thenReturn(ClientBuilder.clientAna2());

        when(bookRepository.getBook(any()))
                .thenReturn(BookBuilder.book1L());

        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        purchase.setClientUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        purchase.setIsCompleted(true);

        String json = mapper.writeValueAsString(purchase);

        this.mockMvc.perform(put(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("27eaa649-e8fa-4889-bd5a-ea6825b71e61")))
                .andExpect(jsonPath("$.clientDTO.uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$.clientDTO.name", is("Ana")))
                .andExpect(jsonPath("$.amount", is(150.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }

    @Test
    void updateWhenIdDontExist() throws Exception {
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();

        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(
                        () -> this.mockMvc.perform(put(this.url + "/df670f4b-5d4d-4f70-ae78-f2ddc9fa1989")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(json))
                                .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Purchase with id df670f4b-5d4d-4f70-ae78-f2ddc9fa1989 not found");
    }

    @Test
    void updateWhenClientDontExist() throws Exception {
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        purchase.setClientUuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1987"));

        String json = mapper.writeValueAsString(purchase);

        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(null);
        when(bookRepository.getBook(any())).thenReturn(BookBuilder.book1L());

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(put(this.url + "/df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id df670f4b-5d4d-4f70-ae78-f2ddc9fa1987 not found");
    }

    @Test
    void updateWhenBookDontExist() throws Exception {
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();

        when(clientRepository.getClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .thenReturn(ClientBuilder.clientAna2());

        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(put(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 12d51c0a-a843-46fc-8447-5fda559ec69b not found");
    }

    @Test
    void deleteTest() throws Exception {
        this.mockMvc.perform(delete(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71987"))
                .andExpect(status().isNoContent());

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(get(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71987"))
                .andExpect(status().isBadRequest()));
    }

    @Test
    void deleteWhenIdDontExistTest() {
        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(delete(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71843"))
                        .andExpect(status().isNotFound()))
                .hasMessageContainingAll("Purchase with id 27eaa649-e8fa-4889-bd5a-ea6825b71843 not found");
    }
}