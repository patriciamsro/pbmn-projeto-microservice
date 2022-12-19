package br.edu.iftm.client.entitie.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDebtDTO {

    private String cpf;
    private Double establishmentValue;
}
