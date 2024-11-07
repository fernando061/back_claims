package com.pe.claims.aplication.Service;

import com.pe.claims.aplication.DTO.EmployeeRegistrationRequestDto;
import com.pe.claims.aplication.Interface.IEmployeeService;
import com.pe.claims.aplication.Mapper.ClaimMapper;
import com.pe.claims.core.Entities.Role;
import com.pe.claims.core.Entities.User;
import com.pe.claims.core.Entities.UserRole;
import com.pe.claims.infraestructure.Service.RoleService;
import com.pe.claims.infraestructure.Service.UserRoleService;
import com.pe.claims.infraestructure.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private ClaimMapper claimMapper;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    PasswordService passwordService;

    @Transactional
    @Override
    public void EmployeeRegister(EmployeeRegistrationRequestDto employeeRegistrationRequestDto) {

        User user = claimMapper.toUser(employeeRegistrationRequestDto);
        String hashedPassword = passwordService.hashPassword(employeeRegistrationRequestDto.getPassword());
        user.setPassword(hashedPassword);

        List<UUID> roleIds = employeeRegistrationRequestDto.getRoleIds();
        Set<UserRole> userRoles = new HashSet<>();
        for (UUID roleId : roleIds) {
            Role role = roleService.findById(roleId);
            UserRole userRole = new UserRole();
            userRole.setId(UUID.randomUUID());
            userRole.setUser(user);
            userRole.setRole(role);
            userRoles.add(userRole);
        }

        userService.save(user);
        userRoleService.saveAll(userRoles.stream().toList());
    }
}
