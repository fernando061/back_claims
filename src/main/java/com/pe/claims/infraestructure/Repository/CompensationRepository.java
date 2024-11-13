package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompensationRepository extends JpaRepository<Compensation, UUID> {
}
