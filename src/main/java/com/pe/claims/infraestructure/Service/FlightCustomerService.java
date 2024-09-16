package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Entities.FlightCustomer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pe.claims.infraestructure.Repository.FlightCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.pe.claims.core.Interfaces.IFlightCustomerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FlightCustomerService extends  GenericService<FlightCustomer> implements IFlightCustomerService {

    @PersistenceContext
    private EntityManager entityManager;

    private final FlightCustomerRepository flightCustomerRepository;

    @Autowired
    public FlightCustomerService(FlightCustomerRepository flightCustomerRepository) {
        this.flightCustomerRepository = flightCustomerRepository;
    }

    @Override
    public FlightCustomer findByFlightNumberAndCustomerDocumentNumber(String flightNumber, String documentNumber) {
        try{
            Optional<FlightCustomer> customResponse = this.flightCustomerRepository.findByFlightNumberAndCustomerDocumentNumber(flightNumber, documentNumber);
            if (customResponse.isPresent()) return customResponse.get();
            throw new RuntimeException("FlightCustomer not found");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}