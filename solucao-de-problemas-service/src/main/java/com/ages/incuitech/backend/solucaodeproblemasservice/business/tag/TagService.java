package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.solucionador.SolucionadorResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.api.tag.TagResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TagService extends GenericCRUDService<Tag, Long> {

    @Inject
    public void setRepository(TagRepository repository) {
        this.repository = repository;
    }

    public List<TagResponse> findAllTags() {
        return this.findAll()
                .stream()
                .map(TagMapper::mapToResponse)
                .collect(Collectors.toList());
    }

    public Tag salvar(Tag tag) {
        try {
        	if (!tagExist(tag)) {
        	  return repository.save(tag);
        	}
        	return null;
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }

	private boolean tagExist(Tag tag) {
		
		List<TagResponse> tags = findAllTags();
		
		for (int i=0;i<tags.size();i++) {
			if (tags.get(i).getNome().equals(tag.getNome())) {
				return true;
			}
		}
		
		return false;
	}
}