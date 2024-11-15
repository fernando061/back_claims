package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Compensation;
import com.pe.claims.core.Interfaces.ICompensationService;
import com.pe.claims.infraestructure.Repository.CompensationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CompensationService implements ICompensationService {

    @Autowired
    CompensationRepository compensationRepository;


    @Override
    public List<Compensation> findAll() {
        return compensationRepository.findAll();
    }

    @Override
    public Compensation findById(UUID id) {
        if(compensationRepository.findById(id).isPresent()){
            return compensationRepository.findById(id).get();
        }
        throw  new RuntimeException("no se encontro ninguna compensation con este id");
    }
}
