package com.pe.claims.aplication.Interface;

import com.pe.claims.core.Entities.Compensation;

import java.util.List;

public interface ICompensationService {
    List<Compensation> findAll();
}
