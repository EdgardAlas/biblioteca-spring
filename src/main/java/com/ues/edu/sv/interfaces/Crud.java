package com.ues.edu.sv.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Crud<Entity, PrimaryKey, SaveDTO, EditDTO> {
    Page<Entity> findAll(Pageable pageable);

    Entity findById(PrimaryKey id);

    Entity  save(SaveDTO entity);

    Entity edit(PrimaryKey id, EditDTO entity);

    void delete(PrimaryKey id);
}
