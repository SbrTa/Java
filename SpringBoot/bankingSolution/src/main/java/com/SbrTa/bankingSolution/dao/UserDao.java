package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}
