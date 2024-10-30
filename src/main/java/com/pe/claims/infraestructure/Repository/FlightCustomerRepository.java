package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.FlightCustomer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Repository
public interface FlightCustomerRepository extends JpaRepository<FlightCustomer, UUID> {
    @EntityGraph(attributePaths = {"customer"})
    @Query("SELECT fc FROM FlightCustomer fc JOIN fc.customer c " +
            "WHERE fc.flightNumber = :flightNumber AND c.documentNumber = :documentNumber")
    Optional<FlightCustomer> findByFlightNumberAndCustomerDocumentNumber(@Param("flightNumber")
                                                                         String flightNumber,
                                                                         @Param("documentNumber")
                                                                         String documentNumber);
    List<FlightCustomer> findByCustomerId(UUID customerId);

    @Query("SELECT fc.id FROM FlightCustomer fc WHERE fc.customer.id = :customerId")
    List<UUID> findIdsByCustomerId(@Param("customerId") UUID customerId);
}
