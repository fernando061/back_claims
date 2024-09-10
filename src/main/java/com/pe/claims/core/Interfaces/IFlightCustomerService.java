package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Customer;
import com.pe.claims.core.Entities.FlightCustomer;

import java.util.Optional;

public interface IFlightCustomerService extends IGenericRepository<FlightCustomer>{
    FlightCustomer findByFlightNumberAndCustomerDocumentNumber(String flightNumber, String documentNumber);
}
