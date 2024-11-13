package com.pe.claims.infraestructure.Repository;

import com.pe.claims.core.Entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})
    User findByEmail(String email);

    @EntityGraph(attributePaths = {"userRoles", "userRoles.role"})
    User findByDocument(String document);
}
