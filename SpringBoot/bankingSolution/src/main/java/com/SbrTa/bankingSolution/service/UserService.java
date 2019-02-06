package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.domain.security.UserRole;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Set;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    boolean checkUserExists(String username, String email);
    void save(User user);
    User saveUser(User user);
    User createUser(User user, Set<UserRole> userRoles);
}
