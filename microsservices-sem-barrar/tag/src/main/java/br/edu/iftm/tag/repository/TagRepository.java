package br.edu.iftm.tag.repository;

import br.edu.iftm.tag.entitie.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends MongoRepository<Tag, String>{

    @Query("{codigo:?0}")
    Optional<Tag> findByCod(String codigo);
}
