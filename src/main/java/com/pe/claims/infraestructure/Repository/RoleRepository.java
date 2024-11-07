package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {


}
