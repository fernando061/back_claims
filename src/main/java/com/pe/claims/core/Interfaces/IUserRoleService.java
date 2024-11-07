package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.UserRole;

import java.util.List;

public interface IUserRoleService {
    void saveAll(List<UserRole> userRoleList);
}
