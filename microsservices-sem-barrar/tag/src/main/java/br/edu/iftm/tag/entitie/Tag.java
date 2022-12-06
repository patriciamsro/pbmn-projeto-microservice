package br.edu.iftm.tag.entitie;

import br.edu.iftm.tag.entitie.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "tag")
public class Tag {

    @Id
    private String id;
    private String codigo;
    private Status status;
    private Date lastModified;

    private String cpf;

    public Tag(String codigo, Status status, Date lastModified) {
        this.codigo = codigo;
        this.status = status;
        this.lastModified = lastModified;
    }
}
