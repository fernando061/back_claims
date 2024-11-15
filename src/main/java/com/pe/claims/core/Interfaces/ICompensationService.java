package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Compensation;

import java.util.List;
import java.util.UUID;

public interface ICompensationService {
    List<Compensation> findAll();
    Compensation findById(UUID id);
}
