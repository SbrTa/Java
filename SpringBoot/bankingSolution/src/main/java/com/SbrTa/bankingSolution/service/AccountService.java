package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import com.SbrTa.bankingSolution.domain.SavingsAccount;

public interface AccountService {
    PrimaryAccount createPrimaryAccount();
    SavingsAccount createSavingsAccount();
}
