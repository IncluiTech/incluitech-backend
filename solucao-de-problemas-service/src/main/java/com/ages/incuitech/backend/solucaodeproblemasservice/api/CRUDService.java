package com.ages.incuitech.backend.solucaodeproblemasservice.api;

import java.util.List;

public interface CRUDService<T, ID> {
    T save(T entity);

    T update(T entity);

    void delete(T entity);

    List<T> findAll();

    T findById(ID id);
}
