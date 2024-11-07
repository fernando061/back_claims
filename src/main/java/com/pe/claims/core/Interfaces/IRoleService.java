package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.Role;

import java.util.List;
import java.util.UUID;

public interface IRoleService {
    Role findById(UUID id);
    List<Role> findAll();
    void saveAll(List<Role> roles);
}
