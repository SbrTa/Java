package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.User;

import javax.print.DocFlavor;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    boolean checkUsernameExists(String username);
    boolean checkEmailExists(String email);
    boolean checkUserExists(String username, String email);
    void save(User user);
}
