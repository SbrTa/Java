package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import org.springframework.data.repository.CrudRepository;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount, Long>{
    PrimaryAccount findByAccountNumber(int accountNumber);
}
