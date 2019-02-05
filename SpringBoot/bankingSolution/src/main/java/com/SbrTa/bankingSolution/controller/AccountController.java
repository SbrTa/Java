package com.SbrTa.bankingSolution.controller;

import com.SbrTa.bankingSolution.domain.*;
import com.SbrTa.bankingSolution.service.AccountService;
import com.SbrTa.bankingSolution.service.TransactionService;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/primaryAccount")
    public String primaryAccount(Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());
        List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        model.addAttribute("primaryAccount",primaryAccount);
        model.addAttribute("primaryTransactionList",primaryTransactionList);
        return "primaryAccount";
    }

    @RequestMapping("/savingsAccount")
    public String savingsAccount(Principal principal, Model model){
        List<SavingsTransaction> savingsTransactionList = transactionService.findSavingsTransactionList(principal.getName());
        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();
        model.addAttribute("savingsAccount",savingsAccount);
        model.addAttribute("savingsTransactionList",savingsTransactionList);
        return "savingsAccount";
    }

    @RequestMapping("/deposit")
    public String deposit(Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String doDeposit(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){
        accountService.deposit(accountType,Double.parseDouble(amount),principal);
        return "redirect:/userFront";
    }

    @RequestMapping("/withdraw")
    public String withdraw(Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount","");
        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String doWithdraw(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){
        accountService.withdraw(accountType,Double.parseDouble(amount),principal);
        return "redirect:/userFront";
    }
}
