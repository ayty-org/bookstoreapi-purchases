package com.bookstoreapi.bookstoreapi.purchase;

import com.bookstoreapi.bookstoreapi.BookstoreApiJacksonApplicationTests;
import com.bookstoreapi.bookstoreapi.builders.PurchaseBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class PurchaseControllerTest extends BookstoreApiJacksonApplicationTests {

    private MockMvc mockMvc;
    @Autowired
    private PurchaseController purchaseController;

    private final String url = "/purchases";
    ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(purchaseController).build();
    }

    @Test
    void saveTest() throws Exception {
        String json = mapper.writeValueAsString(PurchaseBuilder.purchaseRecieve());

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.client.name", is("Jenipapo")))
                .andExpect(jsonPath("$.client.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.amount", is(230.00)))
                .andExpect(jsonPath("$.isCompleted", is(true)));
    }


    @Test
    void saveWhenPurchaseIsInvalid() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setClient(null);
        String json = mapper.writeValueAsString(p);

        this.mockMvc.perform(post(this.url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());

    }

    @Test
    void saveWhenClientDontExist() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setClient(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ecabc"));
        String json = mapper.writeValueAsString(p);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(post(this.url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id 12d51c0a-a843-46fc-8447-5fda559ecabc not found");
    }

    @Test
    void saveWhenBookDontExist() throws Exception {
        PurchaseRecieveDTO p = PurchaseBuilder.purchaseRecieve();
        p.setPurchasedBooks(List.of(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ecabc")));
        String json = mapper.writeValueAsString(p);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(post(this.url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 12d51c0a-a843-46fc-8447-5fda559ecabc not found");
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].client.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$[0].client.name", is("Jenipapo")))
                .andExpect(jsonPath("$[0].amount", is(100.00)))
                .andExpect(jsonPath("$[0].isCompleted", is(true)))
                .andExpect(jsonPath("$[1].uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].client.uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$[1].client.name", is("Ana")))
                .andExpect(jsonPath("$[1].amount", is(200.00)))
                .andExpect(jsonPath("$[1].isCompleted", is(false)));
    }

    @Test
    void getTest() throws Exception {
        mockMvc.perform(get(url + "/12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.client.uuid", is("12d51c0a-a843-46fc-8447-5fda559ec69b")))
                .andExpect(jsonPath("$.client.name", is("Jenipapo")))
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
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        purchase.setClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        purchase.setIsCompleted(true);

        String json = mapper.writeValueAsString(purchase);

        this.mockMvc.perform(put(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.client.uuid", is("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")))
                .andExpect(jsonPath("$.client.name", is("Ana")))
                .andExpect(jsonPath("$.amount", is(230.00)))
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
        purchase.setClient(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1987"));

        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(put(this.url + "/df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Client with id df670f4b-5d4d-4f70-ae78-f2ddc9fa1987 not found");
    }

    @Test
    void updateWhenBookDontExist() throws Exception {
        PurchaseRecieveDTO purchase = PurchaseBuilder.purchaseRecieve();
        List<UUID> ids = new LinkedList<>();
        ids.add(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"));
        ids.add(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"));
        ids.add(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71954"));

        purchase.setPurchasedBooks(ids);
        String json = mapper.writeValueAsString(purchase);

        Assertions.assertThatThrownBy(() -> this.mockMvc.perform(put(this.url + "/27eaa649-e8fa-4889-bd5a-ea6825b71e61")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                        .andExpect(status().isBadRequest()))
                .hasMessageContainingAll("Book with id 27eaa649-e8fa-4889-bd5a-ea6825b71954 not found");
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