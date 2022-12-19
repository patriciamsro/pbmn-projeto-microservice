package br.edu.iftm.establishment.entitie;


import br.edu.iftm.establishment.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private String id;
    private String codigo;
    private Status status;
    private Date lastModified;
    private String cpf;
}
