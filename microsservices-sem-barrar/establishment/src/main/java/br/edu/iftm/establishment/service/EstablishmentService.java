package br.edu.iftm.establishment.service;

import br.edu.iftm.establishment.entitie.Establishment;
import br.edu.iftm.establishment.message.EstablishmentSendMessage;
import br.edu.iftm.establishment.repository.EstablishmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstablishmentService {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    EstablishmentSendMessage establishmentSendMessage;

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public Optional<Establishment> findById(String id) {
        return establishmentRepository.findById(id);
    }

    public Establishment create(Establishment establishment) {
        var reply = establishmentRepository.save(establishment);
        establishmentSendMessage.sendMessage(reply);
        if(reply != null) {

        }
        return reply;
    }

    public Establishment update(Establishment establishment, String id) {
        try {
            if(establishmentRepository.existsById(id)) {
                establishment.setId(id);
                return establishmentRepository.save(establishment);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível ATUALIZAR. Estabelecimento não encontrado!");
        }
        return null;
    }

    public void deleteById(String id) {
        try {
            if(establishmentRepository.existsById(id)) {
                establishmentRepository.deleteById(id);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Estabelecimento não encontrado!");
        }
    }

    public void delete(Establishment establishment) {
        try {
            if(establishment != null && establishmentRepository.existsById(establishment.getId())) {
                establishmentRepository.delete(establishment);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Estabelecimento não encontrado!");
        }
    }
}
