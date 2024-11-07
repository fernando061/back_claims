package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.Interface.IRolesService;
import com.pe.claims.core.Entities.Role;
import com.pe.claims.infraestructure.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService implements IRolesService {

    @Autowired
    RoleService roleService;

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
