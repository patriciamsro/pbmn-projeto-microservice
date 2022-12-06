package br.edu.iftm.tag.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private String id;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private Double balance;
}
