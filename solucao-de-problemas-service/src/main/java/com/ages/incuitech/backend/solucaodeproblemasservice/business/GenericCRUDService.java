package com.ages.incuitech.backend.solucaodeproblemasservice.business;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class GenericCRUDService<T, ID, R extends CrudRepository<T, ID>> implements CRUDService<T, ID> {
    protected R repository;

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
