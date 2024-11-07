package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.Role;
import com.pe.claims.core.Interfaces.IRoleService;
import com.pe.claims.infraestructure.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoleService implements IRoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findById(UUID id) {
        var role =  roleRepository.findById(id);
        if(role.isPresent()) return role.get();
        throw new RuntimeException("customer not found");
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
