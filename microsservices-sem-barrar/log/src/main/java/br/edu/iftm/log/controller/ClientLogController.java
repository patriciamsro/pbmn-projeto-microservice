package br.edu.iftm.log.controller;

import br.edu.iftm.log.entitie.ClientLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.service.ClientLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log/client")
public class ClientLogController {

    @Autowired
    private ClientLogService clientLogService;

    @GetMapping
    public List<ClientLog> findAll() {
        return clientLogService.findAll();
    }

    @GetMapping("/id/{id}")
    public ClientLog findById(@PathVariable String id) {
        return clientLogService.findById(id);
    }

    @GetMapping("/action/{action}")
    public List<ClientLog> findByAction(@PathVariable String action) {
        return clientLogService.findByAction(Action.valueOf(action));
    }
}
