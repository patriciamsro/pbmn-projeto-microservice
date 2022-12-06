package br.edu.iftm.tag.entitie;

import br.edu.iftm.tag.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

    private String codigo;
    private String cpf;
}
