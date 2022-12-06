package br.edu.iftm.client.service;

import br.edu.iftm.client.entitie.Client;
import br.edu.iftm.client.repository.ClientRepository;
import br.edu.iftm.client.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(String id) {
        Client client = clientRepository.findById(id).get();
        if(client == null) {
            throw new ObjectNotFoundException("Cliente não encontrado! ID: " + id
            + ", Tipo: " + Client.class.getName());
        }
        return client;
    }

    public Client findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf).get();
    }

    public Client create(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Client client, String id) {
        try {
            if (clientRepository.existsById(id)) {
                client.setId(id);
                return clientRepository.save(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível ATUALIZAR. Cliente não encontrado!");
        }
        return null;
    }

    public void deleteById(String id) {
        try {
            if (clientRepository.existsById(id)) {
                clientRepository.deleteById(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Cliente não encontrado!");
        }
    }

    public void delete(Client client) {
        try {
            if (client != null && clientRepository.existsById(client.getId())) {
                clientRepository.delete(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Cliente não encontrado!");
        }
    }
}
