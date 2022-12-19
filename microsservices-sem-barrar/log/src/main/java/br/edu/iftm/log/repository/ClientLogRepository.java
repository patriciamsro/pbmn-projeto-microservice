package br.edu.iftm.log.repository;

import br.edu.iftm.log.entitie.ClientLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLogRepository extends MongoRepository<ClientLog, String> {

    @Query("{action: ?0}")
    public List<ClientLog> findByAction(String action);
}
