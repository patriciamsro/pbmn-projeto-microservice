package br.edu.iftm.tag.service;

import br.edu.iftm.tag.entitie.Client;
import br.edu.iftm.tag.entitie.Tag;
import br.edu.iftm.tag.entitie.TagDTO;
import br.edu.iftm.tag.entitie.enums.Status;
import br.edu.iftm.tag.feign.ClientFeign;
import br.edu.iftm.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

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

    public Tag bindTagToClient(TagDTO tagDTO) {

        Tag tag = findByCod(tagDTO.getCodigo()).get();
        Optional<Client> clientFound = clientFeign.findByCpf(tagDTO.getCpf());

        try {
            if(!clientFound.isEmpty())
                tag.setCpf(tagDTO.getCpf());
                tag.setStatus(Status.ACTIVE);
                update(tag, tag.getId());
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("CPF não encontrado, por favor, realize seu cadastro!");
        }
        return tag;
    }

    public Tag update(Tag tag, String id) {
        try {
            if(tagRepository.existsById(id)) {
                tag.setId(id);
                return tagRepository.save(tag);
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
                tagRepository.delete(tag);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Tag não encontrada!");
        }
    }

    public void deleteById(String id) {
        try {
            if(tagRepository.existsById(id)) {
                tagRepository.deleteById(id);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Tag não encontrada!");
        }
    }

    public List<Tag> createDisableTags() {
        Date lastDateModified = new Date();

        Tag tag1 = new Tag("xpto123", Status.INACTIVE, lastDateModified);
        Tag tag2 = new Tag("xpto456", Status.INACTIVE, lastDateModified);
        Tag tag3 = new Tag("xpto789", Status.INACTIVE, lastDateModified);

        List<Tag> tagsDisable = new ArrayList<>(Arrays.asList(tag1, tag2, tag3));

        tagsDisable.forEach(tag -> {
            tagRepository.save(tag);
        });

        return tagsDisable;
    }
}
