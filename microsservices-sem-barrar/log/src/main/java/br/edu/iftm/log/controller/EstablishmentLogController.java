package br.edu.iftm.log.controller;

import br.edu.iftm.log.entitie.EstablishmentLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.service.EstablishmentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log/establishment")
public class EstablishmentLogController {

    @Autowired
    private EstablishmentLogService establishmentLogService;

    @GetMapping
    public List<EstablishmentLog> findAll() {
        return establishmentLogService.findAll();
    }

    @GetMapping("/id/{id}")
    public EstablishmentLog findById(@PathVariable String id) {
        return establishmentLogService.findById(id);
    }

    @GetMapping("/action/{action}")
    public List<EstablishmentLog> findByAction(@PathVariable String action) {
        return establishmentLogService.findByAction(Action.valueOf(action));
    }
}
