package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.PrimaryTransaction;
import com.SbrTa.bankingSolution.domain.SavingsTransaction;

import java.util.List;

public interface TransactionService {
    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);
    void savePrimaryAccountTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsAccountTransaction(SavingsTransaction savingsTransaction);
}
