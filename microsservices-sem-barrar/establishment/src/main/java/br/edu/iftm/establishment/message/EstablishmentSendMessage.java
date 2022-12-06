package br.edu.iftm.establishment.message;

import br.edu.iftm.establishment.entitie.Establishment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentSendMessage {

    @Value("${establishment.rabbitmq.exchange}")
    String exchange;

    @Value("${establishment.rabbitmq.routingkey}")
    String rountingKey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public EstablishmentSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Establishment establishment) {
        rabbitTemplate.convertAndSend(exchange, rountingKey, establishment);
    }
}
