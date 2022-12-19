package br.edu.iftm.log.message;

import br.edu.iftm.log.entitie.EstablishmentLog;
import br.edu.iftm.log.service.EstablishmentLogService;
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
public class EstablishmentLogReceiveMessage {

    @Autowired
    private EstablishmentLogService establishmentLogService;

//   criacao automatica da queue, bind com a exchange e filtro pela routingkey
    @RabbitListener(bindings = @QueueBinding(
            value= @Queue(value = "${sem-barrar.establishment.rabbitmq.queue}", durable = "true"),
            exchange = @Exchange(value = "${sem-barrar.rabbitmq.exchange}"),
            key = "typeEstablishment"))
    public void receive(@Payload EstablishmentLog receivedLog) {
            log.info("Mensagem recebida {}", receivedLog);
            establishmentLogService.createLog(receivedLog);
            log.info("Mensagem salva {}", receivedLog);
    }
}
