package br.edu.iftm.establishment.entitie;


import br.edu.iftm.establishment.entitie.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentLog {
    private Action action = Action.NONE;
    private String establishmentId;
    private String name;
    private String cnpj;
    private String endereco;
}
