package br.edu.iftm.client.message;

import br.edu.iftm.client.entitie.Client;
import br.edu.iftm.client.entitie.ClientLog;
import br.edu.iftm.client.entitie.enums.Action;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClientSendMessageLog {

    @Value("${sem-barrar.rabbitmq.exchange}")
    String exchange;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendMessage(Client client, Action action) {

        ClientLog clientLog = new ClientLog();
        clientLog.setClientId(client.getId());
        clientLog.setAction(action);
        clientLog.setName(client.getName());
        clientLog.setCpf(client.getCpf());
        clientLog.setEmail(client.getEmail());
        clientLog.setAddress(client.getAddress());
        clientLog.setBalance(client.getBalance());

        rabbitTemplate.convertAndSend(exchange, "typeClient", clientLog);
    }
}
