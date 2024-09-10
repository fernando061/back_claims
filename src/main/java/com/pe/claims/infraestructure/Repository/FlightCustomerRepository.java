package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.FlightCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FlightCustomerRepository extends JpaRepository<FlightCustomer, UUID> {

    @Query("SELECT fc FROM FlightCustomer fc JOIN FETCH fc.customer c WHERE fc.flightNumber = :flightNumber AND c.documentNumber = :documentNumber")
    Optional<FlightCustomer> findByFlightNumberAndCustomerDocumentNumber(@Param("flightNumber")
                                                                         String flightNumber,
                                                                         @Param("documentNumber")
                                                                         String documentNumber);
}
