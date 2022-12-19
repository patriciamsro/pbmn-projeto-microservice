package br.edu.iftm.log.entitie;

import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "establishmentlogs")
public class EstablishmentLog {

    @Id
    private String establishmentId;
    private Action action = Action.NONE;
    private String cnpj;
    private Status endereco;
}
