package br.edu.iftm.client.entitie.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDebtResponseDTO {
    private Boolean debited;
    private String message;
}
