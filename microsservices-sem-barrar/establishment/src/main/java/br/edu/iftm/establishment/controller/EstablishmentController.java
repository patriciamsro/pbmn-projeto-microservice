package br.edu.iftm.establishment.controller;

import br.edu.iftm.establishment.entitie.Establishment;
import br.edu.iftm.establishment.service.EstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {

    @Autowired
    private EstablishmentService establishmentService;

    @GetMapping
    public List<Establishment> findAll() {
        return establishmentService.findAll();
    }

    @GetMapping("{id}")
    public Optional<Establishment> findById(@PathVariable String id) {
        return establishmentService.findById(id);
    }

    @PostMapping
    public Establishment create(@Valid @RequestBody Establishment establishment) {
        return establishmentService.create(establishment);
    }

    @PutMapping("{id}")
    public Establishment update(@Valid @RequestBody Establishment establishment, @PathVariable String id) {
        return establishmentService.update(establishment, id);
    }

    @DeleteMapping
    public void delete(@RequestBody Establishment establishment) {
        establishmentService.delete(establishment);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        establishmentService.deleteById(id);
    }
}
