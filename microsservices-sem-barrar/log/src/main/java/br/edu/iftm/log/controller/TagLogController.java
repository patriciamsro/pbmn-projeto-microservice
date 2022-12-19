package br.edu.iftm.log.controller;

import br.edu.iftm.log.entitie.TagLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.service.TagLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log/tag")
public class TagLogController {

    @Autowired
    private TagLogService tagLogService;

    @GetMapping
    public List<TagLog> findAll() {
        return tagLogService.findAll();
    }

    @GetMapping("/id/{id}")
    public TagLog findById(@PathVariable String id) {
        return tagLogService.findById(id);
    }

    @GetMapping("/action/{action}")
    public List<TagLog> findByAction(@PathVariable String action) {
        return tagLogService.findByAction(Action.valueOf(action));
    }
}
