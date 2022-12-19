package br.edu.iftm.log.entitie;

import br.edu.iftm.log.entitie.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "clientlogs")
public class ClientLog {

    @Id
    private String clientId;
    private Action action = Action.NONE;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private Double balance;
}
