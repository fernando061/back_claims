package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface ICustomerService  extends IGenericRepository<Customer>{

}
