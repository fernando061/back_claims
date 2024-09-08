package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Interfaces.ICustomerService;
import com.pe.claims.infraestructure.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService extends  GenericService<Customer> implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer testFindById(UUID id) {
        customerRepository.save(null);
        return null;
    }

}