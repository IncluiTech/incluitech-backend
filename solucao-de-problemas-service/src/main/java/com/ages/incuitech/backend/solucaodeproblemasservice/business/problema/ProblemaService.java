package com.ages.incuitech.backend.solucaodeproblemasservice.business.problema;

import com.ages.incuitech.backend.solucaodeproblemasservice.MailService;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.problema.ProblemaResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.adm.AdministradoresService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.problema.ProblemaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaMapper.fromModelToResponse;
import static com.ages.incuitech.backend.solucaodeproblemasservice.business.problema.ProblemaMapper.fromRequestToModel;

@Service
public class ProblemaService extends GenericCRUDService<Problema, Long, ProblemaRepository> {

    private final MailService mailService;
    private final AdministradoresService administradoresService;


    public ProblemaService(ProblemaRepository repository, MailService mailService, AdministradoresService administradoresService) {
        this.administradoresService = administradoresService;
        this.repository = repository;
        this.mailService = mailService;
    }

    public ProblemaResponse save(ProblemaRequest request) {
        ProblemaResponse problemaResponse = fromModelToResponse(this.save(fromRequestToModel(request)));
        notificarAdministradorCadastroProblema(problemaResponse);
        return problemaResponse;
    }

    public List<ProblemaResponse> findAllOf(Integer clientId) {
        return this.repository.findByIdCliente(clientId).stream()
                .map(ProblemaMapper::fromModelToResponse)
                .collect(Collectors.toList());
    }

    private void notificarAdministradorCadastroProblema(ProblemaResponse problemaResponse) {
        this.mailService.sendTo(this.administradoresService.getEmails(), "Novo problema cadastrado!", "Olá!" +
                "\nGostaríamos de informar que um novo problema foi cadastrado no sistema da IncluiTec." +
                "\nTítulo to problema: "+problemaResponse.getTitulo() +
                "\nDescrição: "+problemaResponse.getDescricao());
    }
}
