package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction,Long> {
    List<SavingsTransaction> findAll();
}
