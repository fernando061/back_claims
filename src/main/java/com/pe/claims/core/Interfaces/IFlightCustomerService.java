package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Complaint;
import com.pe.claims.core.Entities.FlightCustomer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IFlightCustomerService extends IGenericRepository<FlightCustomer>{
    FlightCustomer findByFlightNumberAndCustomerDocumentNumber(String flightNumber, String documentNumber);
    List<FlightCustomer> findByCustomerId(UUID customerId);
    List<UUID> findIdsByCustomerId(UUID customerId);
}
