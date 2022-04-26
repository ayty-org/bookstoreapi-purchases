package br.com.bookstoreapi.purchases.builders;


import br.com.bookstoreapi.purchases.client.ClientDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class ClientBuilder {

    public static ClientDTO clientJenipapo1(){
        return ClientDTO.builder()
                .uuid(UUID.fromString("12d51c0a-a843-46fc-8447-5fda559ec69b"))
                .name("Jenipapo")
                .age(19)
                .email("jenipapo@coldmail.com")
                .telephone("83996438691")
                .gender("Male")
                .build();
    }

    public static ClientDTO clientAna2(){
        return ClientDTO.builder()
                .uuid(UUID.fromString("df670f4b-5d4d-4f70-ae78-f2ddc9fa1f14"))
                .name("Ana")
                .age(46)
                .email("ana@coldmail.com")
                .telephone("83996438691")
                .gender("Female")
                .build();
    }

    public static ClientDTO clientPatricia3(){
        return ClientDTO.builder()
                .uuid(UUID.fromString("27eaa649-e8fa-4889-bd5a-ea6825b71e61"))
                .name("Patricia")
                .age(25)
                .email("patricia@coldmail.com")
                .telephone("83996438691")
                .gender("Trans")
                .build();
    }


    public static List<ClientDTO> clientList(){
        List<ClientDTO> list = new LinkedList<>();
        list.add(clientJenipapo1());
        list.add(clientAna2());
        list.add(clientPatricia3());
        return list;
    }
}
