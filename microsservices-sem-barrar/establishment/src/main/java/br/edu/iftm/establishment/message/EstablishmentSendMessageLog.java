package br.edu.iftm.establishment.message;

import br.edu.iftm.establishment.entitie.Establishment;
import br.edu.iftm.establishment.entitie.EstablishmentLog;
import br.edu.iftm.establishment.entitie.enums.Action;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentSendMessageLog {

    @Value("${sem-barrar.rabbitmq.exchange}")
    String exchange;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendMessage(Establishment establishment, Action action) {

        EstablishmentLog establishmentLog = new EstablishmentLog();
        establishmentLog.setEstablishmentId(establishment.getId());
        establishmentLog.setAction(action);
        establishmentLog.setName(establishment.getName());
        establishmentLog.setCnpj(establishmentLog.getCnpj());
        establishmentLog.setEndereco(establishmentLog.getEndereco());

        rabbitTemplate.convertAndSend(exchange, "typeEstablishment", establishmentLog);

    }
}
