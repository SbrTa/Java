package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import com.SbrTa.bankingSolution.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();

    SavingsAccount createSavingsAccount();

    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal);
}