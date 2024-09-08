package com.pe.claims.core.Interfaces;

import java.util.UUID;

public interface IGenericRepository<T> {
    T testFindById(UUID id);
}
