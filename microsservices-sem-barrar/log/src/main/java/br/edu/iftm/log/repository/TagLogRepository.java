package br.edu.iftm.log.repository;

import br.edu.iftm.log.entitie.TagLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagLogRepository extends MongoRepository<TagLog, String> {

    @Query("{action: ?0}")
    public List<TagLog> findByAction(String action);
}
