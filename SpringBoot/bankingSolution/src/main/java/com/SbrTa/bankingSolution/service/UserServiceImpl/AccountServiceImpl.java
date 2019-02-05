package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.PrimaryAccountDao;
import com.SbrTa.bankingSolution.dao.SavingsAccountDao;
import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import com.SbrTa.bankingSolution.domain.SavingsAccount;
import com.SbrTa.bankingSolution.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private static int nextAccountNumber = 110000000 ;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;


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
}
