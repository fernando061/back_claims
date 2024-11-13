package com.pe.claims.aplication.Service;

import com.pe.claims.core.Entities.Compensation;
import com.pe.claims.core.Interfaces.ICompensationService;
import com.pe.claims.infraestructure.Service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationsService implements ICompensationService {

    @Autowired
    CompensationService compensationService;

    @Override
    public List<Compensation> findAll() {
        return compensationService.findAll();
    }
}
