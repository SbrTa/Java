package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.SavingsAccount;
import org.springframework.data.repository.CrudRepository;

public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {
    SavingsAccount findByAccountNumber(int accountNumber);
}
