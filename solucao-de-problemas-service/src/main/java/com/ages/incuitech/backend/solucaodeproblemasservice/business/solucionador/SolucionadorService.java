package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador.Tag_Solucionador;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.TagSolucionador.Tag_SolucionadorService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SolucionadorService extends GenericCRUDService<Solucionador, Long> {

    @Inject
    public void setRepository(SolucionadorRepository repository) {
        this.repository = repository;
    }

    public List<SolucionadorResponse> findAllSolucionadores() {
        return this.findAll()
                .stream()
                .map(SolucionadorMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public Solucionador salvar(Solucionador solucionador) {
        try {
            SolucionadorRequest solucionadorRequest = null;
            connectTag_Solucionador(solucionadorRequest);
            return repository.save(solucionador);
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }

    private void connectTag_Solucionador(SolucionadorRequest solucionadorRequest) {
        List<String> tags = solucionadorRequest.getTags();
      
        Tag_SolucionadorService tag_solucionador_service = null;
  //      Tag_Solucionador tag_solucionador = new Tag_Solucionador(null, null, null, null, null);
  //      tag_solucionador_service.salvar(tag_solucionador);
        
      //  tag_solucionador.
        /*
        private Long id;
    private Long id_tag;
    private Long id_solucionador;
    private LocalDateTime dataCriacao;
    private StatusCadastro statusCadastro;
         */
    }
}