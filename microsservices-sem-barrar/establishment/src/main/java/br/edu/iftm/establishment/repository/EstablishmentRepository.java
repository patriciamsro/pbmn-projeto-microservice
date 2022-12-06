package br.edu.iftm.establishment.repository;

import br.edu.iftm.establishment.entitie.Establishment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstablishmentRepository extends MongoRepository<Establishment, String> {
}
