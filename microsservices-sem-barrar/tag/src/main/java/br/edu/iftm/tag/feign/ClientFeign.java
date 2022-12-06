package br.edu.iftm.tag.feign;

import br.edu.iftm.tag.entitie.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(value = "client")
public interface ClientFeign {

    @GetMapping("/clients/findcpf/{cpf}")
    public Optional<Client> findByCpf(@PathVariable String cpf);
}
