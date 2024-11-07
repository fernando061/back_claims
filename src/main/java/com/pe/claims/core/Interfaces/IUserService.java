package com.pe.claims.core.Interfaces;

import com.pe.claims.core.Entities.User;

public interface IUserService {

    void save(User user);
    User findByEmail(String email);
}
