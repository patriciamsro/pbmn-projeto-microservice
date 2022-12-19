package br.edu.iftm.tag.controller;

import br.edu.iftm.tag.entitie.Tag;
import br.edu.iftm.tag.entitie.dtos.TagDTO;
import br.edu.iftm.tag.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagservice;

    @GetMapping
    public List<Tag> findAll() {
        return tagservice.findAll();
    }

    @GetMapping("findid/{id}")
    public Optional<Tag> findById(@PathVariable String id) {
        return tagservice.findById(id);
    }

    @GetMapping("findcod/{codigo}")
    public Optional<Tag> findByCod(@PathVariable String codigo) {
        return tagservice.findByCod(codigo);
    }

    @PatchMapping
    public Tag bindTagToClient(@RequestBody TagDTO tagDTO) {
        return tagservice.bindTagToClient(tagDTO);
    }

    @PutMapping("{id}")
    public Tag update(@RequestBody Tag tag, @PathVariable String id) {
        return tagservice.update(tag, id);
    }

    @DeleteMapping
    public void delete(@RequestBody Tag tag) {
        tagservice.delete(tag);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        tagservice.deleteById(id);
    }

    @PostMapping("/createtags")
    public List<Tag> createDisableTags () {
        return tagservice.createDisableTags();
    }
}
