package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.Complaint;
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
public interface ComplaintRepository extends JpaRepository<Complaint, UUID> {

    boolean existsByClaimCode(String claimCode);

    @Query("SELECT c FROM Complaint c JOIN FETCH c.flightCustomer fc JOIN FETCH fc.customer cust " +
            "WHERE c.claimCode = :claimCode AND cust.documentNumber = :documentNumber")
    Optional<Complaint> findByDocumentNumberAndClaimcode(@Param("claimCode")
                                                        String claimCode,
                                                        @Param("documentNumber")
                                                        String documentNumber);

    @Query("SELECT c FROM Complaint c WHERE c.flightCustomerId IN :ids")
    List<Complaint> findByFlightCustomerIdIn(@Param("ids") List<UUID> ids);

    @Override
    @EntityGraph(attributePaths = {"flightCustomer", "flightCustomer.customer"})
    Optional<Complaint> findById(UUID id);
}
