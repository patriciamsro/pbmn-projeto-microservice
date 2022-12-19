package br.edu.iftm.establishment.feign;

import br.edu.iftm.establishment.entitie.dtos.ClientDebtDTO;
import br.edu.iftm.establishment.entitie.dtos.ClientDebtResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "client")
public interface ClientFeign {

    @PostMapping("/clients/deduct")
    public ClientDebtResponseDTO deductBalance(@RequestBody ClientDebtDTO clientDebtDTO);
}
