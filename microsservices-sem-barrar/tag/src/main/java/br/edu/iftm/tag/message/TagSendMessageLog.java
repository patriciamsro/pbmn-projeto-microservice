package br.edu.iftm.tag.message;

import br.edu.iftm.tag.entitie.enums.Action;
import br.edu.iftm.tag.entitie.Tag;
import br.edu.iftm.tag.entitie.TagLog;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TagSendMessageLog {

    @Value("${sem-barrar.rabbitmq.exchange}")
    String exchange;

    @Autowired
    public RabbitTemplate rabbitTemplate;

    public void sendMessage(Tag tag, Action action) {

        TagLog tagLog = new TagLog();
        tagLog.setTagId(tag.getId());
        tagLog.setAction(action);
        tagLog.setCodigo(tag.getCodigo());
        tagLog.setStatus(tag.getStatus());
        tagLog.setLastModified(tag.getLastModified());
        tagLog.setCpf(tag.getCpf());

        rabbitTemplate.convertAndSend(exchange, "typeTag", tagLog);
    }
}
