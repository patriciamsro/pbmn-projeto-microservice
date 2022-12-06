package br.edu.iftm.client.repository;

import br.edu.iftm.client.entitie.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    @Query("findcpf/{cpf:?0}")
    Optional<Client> findByCpf(String cpf);
}
