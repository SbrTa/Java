package com.SbrTa.bankingSolution.service;

import com.SbrTa.bankingSolution.domain.*;

import java.security.Principal;
import java.util.List;

public interface TransactionService {
    List<PrimaryTransaction> findPrimaryTransactionList(String username);
    List<SavingsTransaction> findSavingsTransactionList(String username);
    void savePrimaryAccountTransaction(PrimaryTransaction primaryTransaction);
    void saveSavingsAccountTransaction(SavingsTransaction savingsTransaction);

    void transferBetweenAccounts(String from, String to, String amount,
                            PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception;

    List<Recipient> findRecipientList(Principal principal);
    Recipient saveRecipient(Recipient recipient);
    Recipient findRecipientByName(String recipientName);
    void deleteRecipientByName(String recipientname);

    void transferToSomeoneElse(Recipient recipient, String accountType, String amount,
                               PrimaryAccount primaryAccount, SavingsAccount savingsAccount);
}
