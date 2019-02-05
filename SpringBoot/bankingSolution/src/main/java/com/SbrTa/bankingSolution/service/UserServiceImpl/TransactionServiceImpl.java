package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.PrimaryTransactionDao;
import com.SbrTa.bankingSolution.dao.SavingsTransactionDao;
import com.SbrTa.bankingSolution.domain.PrimaryTransaction;
import com.SbrTa.bankingSolution.domain.SavingsTransaction;
import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.service.TransactionService;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

    public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList =
                user.getPrimaryAccount().getPrimaryTransactionList();
        return primaryTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username){
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList =
                user.getSavingsAccount().getSavingsTransactionList();
        return savingsTransactionList;
    }

    public void savePrimaryAccountTransaction(PrimaryTransaction primaryTransaction){
        primaryTransactionDao.save(primaryTransaction);
    }

    public void saveSavingsAccountTransaction(SavingsTransaction savingsTransaction){
        savingsTransactionDao.save(savingsTransaction);
    }

}
