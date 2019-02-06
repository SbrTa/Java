package com.SbrTa.bankingSolution.service.UserServiceImpl;

import com.SbrTa.bankingSolution.dao.*;
import com.SbrTa.bankingSolution.domain.*;
import com.SbrTa.bankingSolution.service.TransactionService;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private UserService userService;

    @Autowired
    private PrimaryTransactionDao primaryTransactionDao;

    @Autowired
    private SavingsTransactionDao savingsTransactionDao;

    @Autowired
    private PrimaryAccountDao primaryAccountDao;

    @Autowired
    private SavingsAccountDao savingsAccountDao;

    @Autowired
    private RecipientDao recipientDao;

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

    public void transferBetweenAccounts(String from, String to, String amount,
                                        PrimaryAccount primaryAccount, SavingsAccount savingsAccount) throws Exception {
        if (from.equalsIgnoreCase("Primary") && to.equalsIgnoreCase("Savings")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            primaryTransactionDao.save(new PrimaryTransaction(new Date(),
                    from + " to " + to,"Account","Finished",Double.parseDouble(amount),
                    primaryAccount.getAccountBalance(),primaryAccount));
            savingsTransactionDao.save(new SavingsTransaction(new Date(),
                    from + " to " + to,"Account","Finished",Double.parseDouble(amount),
                    savingsAccount.getAccountBalance(),savingsAccount));
        }else if (from.equalsIgnoreCase("Savings") && to.equalsIgnoreCase("primary")){
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
            savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            savingsAccountDao.save(savingsAccount);
            primaryTransactionDao.save(new PrimaryTransaction(new Date(),
                    from + " to " + to,"Account","Finished",Double.parseDouble(amount),
                    primaryAccount.getAccountBalance(),primaryAccount));
            savingsTransactionDao.save(new SavingsTransaction(new Date(),
                    from + " to " + to,"Account","Finished",Double.parseDouble(amount),
                    savingsAccount.getAccountBalance(),savingsAccount));
        } else {
            throw new Exception("Invalid Transfer...");
        }
    }

    public List<Recipient> findRecipientList(Principal principal){
        String username = principal.getName();
        List<Recipient> recipientList = recipientDao.findAll()
                .stream()
                .filter(recipient -> username.equals(recipient.getUser().getUsername()))
                .collect(Collectors.toList());
        return recipientList;
    }

    public Recipient saveRecipient(Recipient recipient){
        return recipientDao.save(recipient);
    }

    public Recipient findRecipientByName(String recipientName){
        return recipientDao.findByName(recipientName);
    }

    public void deleteRecipientByName(String recipientname){
        recipientDao.deleteByName(recipientname);
    }

    public void transferToSomeoneElse(Recipient recipient, String accountType, String amount,
                                      PrimaryAccount primaryAccount, SavingsAccount savingsAccount) {
        if (accountType.equalsIgnoreCase("Primary")) {
            primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            primaryAccountDao.save(primaryAccount);
            primaryTransactionDao.save(new PrimaryTransaction(new Date(), "Transfer to " + recipient.getName(),
                    "Transfer", "Finished", Double.parseDouble(amount), primaryAccount.getAccountBalance(), primaryAccount));
        } else if (accountType.equalsIgnoreCase("Savings")) {
            savingsAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
            savingsAccountDao.save(savingsAccount);
            savingsTransactionDao.save(new SavingsTransaction(new Date(), "Transfer to " + recipient.getName(),
                    "Transfer", "Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount));
        }
    }

}
