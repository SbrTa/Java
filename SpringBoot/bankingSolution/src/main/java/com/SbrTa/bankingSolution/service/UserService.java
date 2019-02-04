package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.User;

import javax.print.DocFlavor;

public interface UserService {
    User findByUserName(String userName);
    User findByEmail(String email);
    boolean checkUserNameExists(String userName);
    boolean checkEmailExists(String email);
    boolean checkUserExists(String userName, String email);
    void save(User user);
}
