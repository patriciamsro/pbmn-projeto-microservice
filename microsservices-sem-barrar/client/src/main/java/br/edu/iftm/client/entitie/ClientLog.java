package br.edu.iftm.client.entitie;

import br.edu.iftm.client.entitie.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientLog {
    private Action action = Action.NONE;
    private String clientId;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private Double balance;
}
