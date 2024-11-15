package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByEmail(String email);
    Optional<Customer> findByDocumentNumber(String documentNumber);


    //@Query("SELECT c FROM Customer c JOIN FETCH c.flightCustomers fc JOIN FETCH fc.complaints com")
    @EntityGraph(attributePaths = {"flightCustomers", "flightCustomers.complaints"})
    @Query("SELECT c FROM Customer c")
    List<Customer> findAllWhereComplaintExist();

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.email = :email WHERE c.id = :id")
    void updateEmailById(@Param("id") UUID id, @Param("email") String email);
}
