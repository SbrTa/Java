package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.PrimaryAccountDao;
import com.SbrTa.bankingSolution.dao.SavingsAccountDao;
import com.SbrTa.bankingSolution.domain.*;
import com.SbrTa.bankingSolution.service.AccountService;
import com.SbrTa.bankingSolution.service.TransactionService;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {
    private static int nextAccountNumber = 110000000 ;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Override
    public PrimaryAccount createPrimaryAccount() {
        PrimaryAccount primaryAccount = new PrimaryAccount();
        primaryAccount.setAccountNumber(accountGen());
        primaryAccount.setAccountBalance(new BigDecimal(0.0));

        primaryAccountDao.save(primaryAccount);
        return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
    }

    @Override
    public SavingsAccount createSavingsAccount() {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountNumber(accountGen());
        savingsAccount.setAccountBalance(new BigDecimal(0.0));

        savingsAccountDao.save(savingsAccount);
        return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
    }

    private int accountGen(){
        return ++nextAccountNumber;
    }

    public void deposit(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to primary account",
                    "Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
            transactionService.savePrimaryAccountTransaction(primaryTransaction);
        }else {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposite to savings account",
                    "Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
            transactionService.saveSavingsAccountTransaction(savingsTransaction);
        }
    }

    public void withdraw(String accountType, double amount, Principal principal){
        User user = userService.findByUsername(principal.getName());
        if (accountType.equalsIgnoreCase("Primary")){
            PrimaryAccount primaryAccount = user.getPrimaryAccount();
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            Date date = new Date();
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from primary account",
                    "Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
            transactionService.savePrimaryAccountTransaction(primaryTransaction);
        }else {
            SavingsAccount savingsAccount = user.getSavingsAccount();
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            Date date = new Date();
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings account",
                    "Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);
            transactionService.saveSavingsAccountTransaction(savingsTransaction);
        }
    }
}
