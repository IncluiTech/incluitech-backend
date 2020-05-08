package com.ages.incuitech.backend.solucaodeproblemasservice.business.tag;

import com.ages.incuitech.backend.solucaodeproblemasservice.api.tag.TagResponse;
import com.ages.incuitech.backend.solucaodeproblemasservice.business.GenericCRUDService;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tags.TagRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    public Tag salvar(String tag) {
        try {
            Optional<Tag> tagOptional = buscarTagPorNome(tag);
        	if (tagOptional.isEmpty()) {
        	    return repository.save(Tag.builder().nome(tag).dataCriacao(LocalDateTime.now()).build());
        	}
        	return tagOptional.get();
        } catch (IllegalArgumentException exception) {
            log.error("Erro ao salvar Solucionador: dados incorretos.");
            throw exception;
        } catch (DataAccessException exception) {
            log.error("Erro ao salvar Solucionador: {}", exception.toString());
            throw exception;
        }
    }

    private Optional<Tag> buscarTagPorNome(String tag) {
        Iterable<Tag> tags = repository.findAll();
		for (Tag tagLista: tags) {
			if (tagLista.equals(tag)) {
				return Optional.of(tagLista);
			}
		}
		return Optional.empty();
	}

}