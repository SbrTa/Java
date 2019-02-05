package com.SbrTa.bankingSolution.dao;

import com.SbrTa.bankingSolution.domain.PrimaryTransaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction,Long> {
    List<PrimaryTransaction> findAll();
}
