package com.ages.incuitech.backend.solucaodeproblemasservice.business;

import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.solucionador.SolucionadorRepository;
import com.ages.incuitech.backend.solucaodeproblemasservice.infrastructure.tag_solucionador.Tag_SolucionadorRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class GenericCRUDService<T, ID> implements CRUDService<T, ID> {
    protected CrudRepository<T, ID> repository;

    public T save(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {
        return (List<T>) repository.findAll();
    }

    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public T update(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}
