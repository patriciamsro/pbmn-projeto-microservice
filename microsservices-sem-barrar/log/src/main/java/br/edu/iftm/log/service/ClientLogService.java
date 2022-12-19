package br.edu.iftm.log.service;

import br.edu.iftm.log.entitie.ClientLog;
import br.edu.iftm.log.entitie.enums.Action;
import br.edu.iftm.log.repository.ClientLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientLogService {

    @Autowired
    private ClientLogRepository clientLogRepository;

    public List<ClientLog> findAll() {
        return clientLogRepository.findAll();
    }

    public ClientLog findById(String id) {
        return clientLogRepository.findById(id).stream().findFirst().orElse(null);
    }

    public List<ClientLog> findByAction(Action action) {
        return clientLogRepository.findByAction(action.toString());
    }

    public ClientLog createLog(ClientLog log) {
        return clientLogRepository.save(log);
    }
}
