package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Customer;

import java.util.List;
import java.util.UUID;

public interface ICustomerService  extends IGenericRepository<Customer>{
    Customer findByDocumentNumber(String documentNumber);
    Customer findById(UUID id);
    List<Customer> findAllWhereComplaintExist();
}
