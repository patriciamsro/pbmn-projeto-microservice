package br.edu.iftm.log.service;

import br.edu.iftm.log.entitie.TagLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.repository.TagLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagLogService {

    @Autowired
    private TagLogRepository tagLogRepository;

    public List<TagLog> findAll() {
        return tagLogRepository.findAll();
    }

    public TagLog findById(String id) {
        return tagLogRepository.findById(id).stream().findFirst().orElse(null);
    }

    public List<TagLog> findByAction(Action action) {
        return tagLogRepository.findByAction(action.toString());
    }

    public TagLog createLog(TagLog log) {
        return tagLogRepository.save(log);
    }
}
