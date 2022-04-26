package br.com.bookstoreapi.purchases.client;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDTO implements Serializable {

    private UUID uuid;
    private String name;
    private Integer age;
    private String telephone;
    private String email;
    private String gender;
}
