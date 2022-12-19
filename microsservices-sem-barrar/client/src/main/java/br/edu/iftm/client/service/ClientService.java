package br.edu.iftm.client.service;

import br.edu.iftm.client.entitie.Client;
import br.edu.iftm.client.entitie.dtos.ClientDebtDTO;
import br.edu.iftm.client.entitie.dtos.ClientDebtResponseDTO;
import br.edu.iftm.client.entitie.enums.Action;
import br.edu.iftm.client.message.ClientSendMessageLog;
import br.edu.iftm.client.repository.ClientRepository;
import br.edu.iftm.client.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientSendMessageLog clientSendMessageLog;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(String id) {
        Client client = clientRepository.findById(id).stream().findFirst().orElse(null);
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
        client.setBalance(0.0);
        clientSendMessageLog.sendMessage(client, Action.ADD);
        return clientRepository.save(client);
    }

    public Client update(Client client, String id) {
        try {
            if (clientRepository.existsById(id)) {
                client.setId(id);
                var updatedClient = clientRepository.save(client);
                clientSendMessageLog.sendMessage(updatedClient, Action.UPDATE);
                return updatedClient;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível ATUALIZAR. Cliente não encontrado!");
        }
        return null;
    }

    public void deleteById(@NotNull String id) {
        try {
            if (clientRepository.existsById(id)) {
                Client clientFound = clientRepository.findById(id).get();
                clientSendMessageLog.sendMessage(clientFound, Action.REMOVE);
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
                clientSendMessageLog.sendMessage(client, Action.REMOVE);
                clientRepository.delete(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Cliente não encontrado!");
        }
    }

    public ClientDebtResponseDTO deductBalance(ClientDebtDTO clientDebtDTO) {
        ClientDebtResponseDTO deductClient = new ClientDebtResponseDTO();
        Optional<Client> client = clientRepository.findByCpf(clientDebtDTO.getCpf());

        if(!client.isEmpty()) {
            Double balance = client.get().getBalance();
            Double debtValue = clientDebtDTO.getEstablishmentValue();

            if(balance > debtValue) {
                client.get().setBalance(balance - debtValue);
                create(client.get());
                clientSendMessageLog.sendMessage(client.get(), Action.UPDATE);
                deductClient.setDebited(true);
                deductClient.setMessage("Valor debitado com sucesso!");
            } else {
                deductClient.setDebited(false);
                deductClient.setMessage("Saldo insuficiente!");
            }
        } else {
            deductClient.setDebited(false);
            deductClient.setMessage("Cliente não encontrado!");
        }
        return deductClient;
    }
}
