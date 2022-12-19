package br.edu.iftm.tag.service;

import br.edu.iftm.tag.entitie.Client;
import br.edu.iftm.tag.entitie.Tag;
import br.edu.iftm.tag.entitie.dtos.TagDTO;
import br.edu.iftm.tag.entitie.enums.Action;
import br.edu.iftm.tag.entitie.enums.Status;
import br.edu.iftm.tag.feign.ClientFeign;
import br.edu.iftm.tag.message.TagSendMessageLog;
import br.edu.iftm.tag.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class TagService {

    public static final int NUMBER_DISABLE_TAGS = 10;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagSendMessageLog tagSendMessageLog;

    @Autowired
    private ClientFeign clientFeign;

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Optional<Tag> findById(String id) {
        return tagRepository.findById(id);
    }

    public Optional<Tag> findByCod(String codigoTag) {
        return tagRepository.findByCod(codigoTag);
    }

    public Tag bindTagToClient(@NotNull TagDTO tagDTO) {
        Tag tag = findByCod(tagDTO.getCodigo()).get();
        Optional<Client> clientFound = clientFeign.findByCpf(tagDTO.getCpf());

        try {
            if(!clientFound.isEmpty())
                tag.setCpf(tagDTO.getCpf());
                tag.setStatus(Status.ACTIVE);
                update(tag, tag.getId());

                log.info("Enviando mensagem {}", tag);
                tagSendMessageLog.sendMessage(tag, Action.UPDATE);
                log.info("Mensagem enviada {}", tag);
            return tag;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("CPF não encontrado, por favor, realize seu cadastro!");
        }
        return null;
    }

    public Tag update(Tag tag, String id) {
        try {
            if(tagRepository.existsById(id)) {
                tag.setId(id);

                var updatedTag = tagRepository.save(tag);
                tagSendMessageLog.sendMessage(updatedTag, Action.UPDATE);

                return updatedTag;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível ATUALIZAR. Tag não encontrada!");
        }
        return null;
    }

    public void delete(Tag tag) {
        try {
            if(tag != null && tagRepository.existsById(tag.getId())) {
                tagSendMessageLog.sendMessage(tag, Action.REMOVE);
                tagRepository.delete(tag);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Tag não encontrada!");
        }
    }

    public void deleteById(@NotNull String id) {
        try {
            if(tagRepository.existsById(id)) {
                var tagFound = tagRepository.findById(id).get();
                tagSendMessageLog.sendMessage(tagFound, Action.REMOVE);
                tagRepository.deleteById(id);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Tag não encontrada!");
        }
    }

    public List<Tag> createDisableTags() {
        Random randomNumber = new Random();
        List<Tag> tagsDisable = new ArrayList<>();

        for(int i=0; i<= NUMBER_DISABLE_TAGS; i++) {
            String nameTag = "xpto" + randomNumber.nextInt(100) + + randomNumber.nextInt(100);
            Tag tagCreated = new Tag(nameTag, Status.INACTIVE, new Date());
            tagsDisable.add(tagCreated);
        }

        tagsDisable.forEach(tag -> {
            log.info("Enviando mensagem {}", tag);
            tagSendMessageLog.sendMessage(tag, Action.ADD);
            log.info("Mensagem enviada {}", tag);
            tagRepository.save(tag);
        });

        return tagsDisable;
    }
}