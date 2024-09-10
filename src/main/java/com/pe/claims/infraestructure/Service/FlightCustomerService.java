package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Entities.FlightCustomer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.pe.claims.infraestructure.Repository.FlightCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.pe.claims.core.Interfaces.IFlightCustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
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
            List<FlightCustomer> lst =  this.flightCustomerRepository.findByFlightNumberAndCustomerDocumentNumber(flightNumber,documentNumber).stream().toList();
            Customer customer = lst.get(0).getCustomer();
            return lst.get(0);//entityManager.createQuery(query).getResultList().stream().findFirst();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}