package br.edu.iftm.tag.entitie;

import br.edu.iftm.tag.entitie.enums.Action;
import br.edu.iftm.tag.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagLog {
    private Action action = Action.NONE;
    private String tagId;
    private String codigo;
    private Status status;
    private Date lastModified;
    private String cpf;
}
