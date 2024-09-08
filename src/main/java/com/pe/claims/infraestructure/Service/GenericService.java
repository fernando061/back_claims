package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Interfaces.IGenericRepository;

import java.util.UUID;

public class GenericService<T>  implements IGenericRepository<T> {

    @Override
    public T testFindById(UUID id) {
        return null;
    }
}
