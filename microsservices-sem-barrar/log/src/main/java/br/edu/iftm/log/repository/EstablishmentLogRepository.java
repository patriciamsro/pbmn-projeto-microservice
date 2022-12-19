package br.edu.iftm.log.repository;

import br.edu.iftm.log.entitie.EstablishmentLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentLogRepository extends MongoRepository<EstablishmentLog, String> {

    @Query("{action: ?0}")
    public List<EstablishmentLog> findByAction(String action);
}
