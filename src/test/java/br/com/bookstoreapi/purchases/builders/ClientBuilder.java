package com.bookstoreapi.bookstoreapi.builders;

import com.bookstoreapi.bookstoreapi.client.Client;
import com.bookstoreapi.bookstoreapi.client.ClientRecieveDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ClientBuilder {

    public static Client clientJenipapo1(){
        return Client.builder()
                .id(1L)
                .uuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .name("Jenipapo")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }

    public static Client clientAna2(){
        return Client.builder()
                .id(2L)
                .uuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"))
                .name("Ana")
                .age(46)
                .email("ana@coldmail.com")
                .telephone("83996438691")
                .gender("Female")
                .build();
    }

    public static Client clientPatricia3(){
        return Client.builder()
                .id(3L)
                .uuid(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61"))
                .name("Patricia")
                .age(25)
                .email("patricia@coldmail.com")
                .telephone("83996438691")
                .gender("Trans")
                .build();
    }

    public static ClientRecieveDTO clientInvalid(){
        return ClientRecieveDTO.builder()
                .name("a")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }
    public static ClientRecieveDTO clientJenipapoRecieve(){
        return ClientRecieveDTO.builder()
                .name("Jenipapo")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }

    public static List<Client> clientList(){
        List<Client> list = new LinkedList<>();
        list.add(clientJenipapo1());
        list.add(clientAna2());
        list.add(clientPatricia3());
        return list;
    }
}
