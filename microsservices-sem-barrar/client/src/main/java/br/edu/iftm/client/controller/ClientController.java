package br.edu.iftm.client.controller;

import br.edu.iftm.client.entitie.Client;
import br.edu.iftm.client.entitie.dtos.ClientDebtDTO;
import br.edu.iftm.client.entitie.dtos.ClientDebtResponseDTO;
import br.edu.iftm.client.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("findid/{id}")
    public ResponseEntity<Client> findById(@PathVariable String id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping("findcpf/{cpf}")
    public ResponseEntity<Client> findByCpf(@PathVariable String cpf) {
        return new ResponseEntity<>(clientService.findByCpf(cpf), HttpStatus.OK);
    }

    @PostMapping
    public Client create(@Valid @RequestBody Client client) {
        return clientService.create(client);
    }

    @PutMapping("{id}")
    public Client update(@Valid @RequestBody Client client, @PathVariable String id) {
        return clientService.update(client, id);
    }

    @DeleteMapping
    public void delete(@RequestBody Client client) {
        clientService.delete(client);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        clientService.deleteById(id);
    }

    @PostMapping("/deduct")
    public ClientDebtResponseDTO deductBalance(@RequestBody ClientDebtDTO clientDebtDTO) {
        return clientService.deductBalance(clientDebtDTO);
    }
}
