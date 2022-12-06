package br.edu.iftm.establishment.entitie;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "establishment")
public class Establishment {

    @Id
    private String id;

    @NotEmpty(message = "O NOME deve ser preenchido!")
    private String name;

    @NotEmpty(message = "o CNPJ deve ser preenchido!")
    @Size(max = 14, message = "O CNPJ deve conter 14 números.")
    private String cnpj;

    private String endereco;
}
