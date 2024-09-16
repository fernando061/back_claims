package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, UUID> {

    boolean existsByClaimCode(String claimCode);

}
