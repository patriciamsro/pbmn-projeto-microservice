package br.edu.iftm.establishment.entitie;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstablishmentAccess {
    private Boolean authorized;
    private String message;
}
