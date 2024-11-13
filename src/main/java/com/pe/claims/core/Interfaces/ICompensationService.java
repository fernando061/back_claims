package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Compensation;

import java.util.List;

public interface ICompensationService {
    List<Compensation> findAll();
}
