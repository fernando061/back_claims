package com.pe.claims.infraestructure.Service;

import com.pe.claims.core.Entities.User;
import com.pe.claims.core.Interfaces.IUserService;
import com.pe.claims.infraestructure.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
