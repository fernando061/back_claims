package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.UserRole;
import com.pe.claims.core.Interfaces.IUserRoleService;
import com.pe.claims.infraestructure.Repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService implements IUserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void saveAll(List<UserRole> userRoleList) {
        userRoleRepository.saveAll(userRoleList);
    }
}
