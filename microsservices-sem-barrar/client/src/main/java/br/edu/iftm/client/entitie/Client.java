package br.edu.iftm.client.entitie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "client")
public class Client {

    @Id
    private String id;
    @NotEmpty(message = "O NOME deve ser preenchido!")
    private String name;

    @NotEmpty(message = "o CPF deve ser preenchido!")
    @Size(max = 11, message = "O CPF deve conter 11 números.")
    private String cpf;

    @NotEmpty(message = "o EMAIL deve ser preenchido!")
    @Email
    private String email;
    private String address;
    private Double balance;

    public Client(String name, String cpf, String email, String address) {
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.address = address;
    }

    private void rechargeBalance(Double balance) {
        this.balance = this.balance + balance;
    }
}
