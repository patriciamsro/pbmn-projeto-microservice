package br.edu.iftm.log.entitie;

import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "taglogs")
public class TagLog {

    @Id
    private String tagId;
    private Action action = Action.NONE;
    private String codigo;
    private Status status;
    private Date lastModified;
    private String cpf;
}
