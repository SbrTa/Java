package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}
