package br.edu.iftm.log.service;

import br.edu.iftm.log.entitie.EstablishmentLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.repository.EstablishmentLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstablishmentLogService {

    @Autowired
    private EstablishmentLogRepository establishmentLogRepository;

    public List<EstablishmentLog> findAll() {
        return establishmentLogRepository.findAll();
    }

    public EstablishmentLog findById(String id) {
        return establishmentLogRepository.findById(id).stream().findFirst().orElse(null);
    }

    public List<EstablishmentLog> findByAction(Action action) {
        return establishmentLogRepository.findByAction(action.toString());
    }

    public EstablishmentLog createLog(EstablishmentLog log) {
        return establishmentLogRepository.save(log);
    }
}
