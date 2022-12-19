package br.edu.iftm.establishment.service;

import br.edu.iftm.establishment.entitie.*;
import br.edu.iftm.establishment.entitie.dtos.ClientDebtDTO;
import br.edu.iftm.establishment.entitie.dtos.ClientDebtResponseDTO;
import br.edu.iftm.establishment.entitie.dtos.CodigoTagDTO;
import br.edu.iftm.establishment.entitie.enums.Action;
import br.edu.iftm.establishment.entitie.enums.Status;
import br.edu.iftm.establishment.feign.ClientFeign;
import br.edu.iftm.establishment.feign.TagFeign;
import br.edu.iftm.establishment.message.EstablishmentSendMessageLog;
import br.edu.iftm.establishment.repository.EstablishmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EstablishmentService {

    private static final Double ESTABLISHMENT_VALUE = 10.00;
    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    EstablishmentSendMessageLog establishmentSendMessage;

    @Autowired
    private TagFeign tagFeign;

    @Autowired
    private ClientFeign clientFeign;

    public List<Establishment> findAll() {
        return establishmentRepository.findAll();
    }

    public Optional<Establishment> findById(String id) {
        return establishmentRepository.findById(id);
    }

    public Establishment create(Establishment establishment) {
        var response = establishmentRepository.save(establishment);
        establishmentSendMessage.sendMessage(response, Action.ADD);
        return response;
    }

    public Establishment update(Establishment establishment, String id) {
        try {
            if(establishmentRepository.existsById(id)) {
                establishment.setId(id);
                establishmentSendMessage.sendMessage(establishment, Action.UPDATE);
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
            Optional<Establishment> establishmentFound = findById(id);
            if(establishmentFound.isPresent()) {
                establishmentRepository.deleteById(id);
                establishmentSendMessage.sendMessage(establishmentFound.get(), Action.REMOVE);
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
                establishmentSendMessage.sendMessage(establishment, Action.REMOVE);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Não foi possível EXCLUIR. Estabelecimento não encontrado!");
        }
    }

    //verifica se tag utilizada no veiculo existe e o status é ativo
    public EstablishmentAccess authorizeEntry(@NotNull CodigoTagDTO codigoTag) {
        EstablishmentAccess access = new EstablishmentAccess();

        String codigo = codigoTag.getCodigo();
        Optional<Tag> tagFound = tagFeign.findByCod(codigo);

        if(tagFound.isEmpty() || tagFound.get().getStatus().equals(Status.INACTIVE)) {
            access.setAuthorized(false);
            access.setMessage("Acesso não autorizado!");
        } else {
            access.setAuthorized(true);
            access.setMessage("Acesso autorizado!");
        }
        return access;
    }

    //verifica se o cliente possui saldo suficiente para debitar valor do estacionamento
    public EstablishmentAccess authorizeExit(@NotNull CodigoTagDTO codigoTag) {
        EstablishmentAccess access = new EstablishmentAccess();

        String codigo = codigoTag.getCodigo();
        Optional<Tag> tagFound = tagFeign.findByCod(codigo);

        ClientDebtDTO client = new ClientDebtDTO(tagFound.get().getCpf(),ESTABLISHMENT_VALUE);
        ClientDebtResponseDTO deductBalance = clientFeign.deductBalance(client);

        access.setAuthorized(deductBalance.getDebited());
        access.setMessage(deductBalance.getMessage());

        return access;
    }
}
