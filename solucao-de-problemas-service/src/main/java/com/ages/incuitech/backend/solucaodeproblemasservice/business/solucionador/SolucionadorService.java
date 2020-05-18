package com.ages.incuitech.backend.solucaodeproblemasservice.business.solucionador;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorRequest;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SolucionadorService
    extends GenericCRUDService<Solucionador, Long, SolucionadorRepository> {

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
      return repository.save(solucionador);
    } catch (IllegalArgumentException exception) {
      log.error("Erro ao salvar Solucionador: dados incorretos.");
      throw exception;
    } catch (DataAccessException exception) {
      log.error("Erro ao salvar Solucionador: {}", exception.toString());
      throw exception;
    }
  }

  public SolucionadorResponse findByFacebookId(String facecbookId) {
    Solucionador solucionador = this.repository.findByIdFacebook(facecbookId);
    return solucionador != null ? SolucionadorMapper.mapToResponse(solucionador) : null;
  }

  public SolucionadorResponse update(SolucionadorRequest request) {
    Solucionador entity = this.repository.findByIdFacebook(request.getFacebookId());
    request.setId(entity.getId());
    Solucionador updated = this.update(SolucionadorMapper.mapToModel(request));
    return SolucionadorMapper.mapToResponse(updated);
  }
}
