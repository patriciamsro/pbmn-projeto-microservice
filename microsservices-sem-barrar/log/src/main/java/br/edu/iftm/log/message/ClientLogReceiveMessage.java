package br.edu.iftm.log.message;

import br.edu.iftm.log.entitie.ClientLog;
import br.edu.iftm.log.service.ClientLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientLogReceiveMessage {

    @Autowired
    private ClientLogService clientLogService;

//   criacao automatica da queue, bind com a exchange e filtro pela routingkey
    @RabbitListener(bindings = @QueueBinding(
            value= @Queue(value = "${sem-barrar.client.rabbitmq.queue}", durable = "true"),
            exchange = @Exchange(value = "${sem-barrar.rabbitmq.exchange}"),
            key = "typeClient"))
    public void receive(@Payload ClientLog receivedLog) {
            log.info("Mensagem recebida {}", receivedLog);
            clientLogService.createLog(receivedLog);
            log.info("Mensagem salva {}", receivedLog);
    }
}
