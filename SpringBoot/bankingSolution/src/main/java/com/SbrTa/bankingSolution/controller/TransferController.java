package com.SbrTa.bankingSolution.controller;


import com.SbrTa.bankingSolution.domain.PrimaryAccount;
import com.SbrTa.bankingSolution.domain.Recipient;
import com.SbrTa.bankingSolution.domain.SavingsAccount;
import com.SbrTa.bankingSolution.domain.User;
import com.SbrTa.bankingSolution.service.TransactionService;
import com.SbrTa.bankingSolution.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserService userService;

    @RequestMapping("/betweenAccounts")
    public String transferBetweenAccounts(Model model){
        model.addAttribute("transferFrom","");
        model.addAttribute("transferTo","");
        model.addAttribute("amount","");
        model.addAttribute("error","");
        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String doTransferBetweenAccounts(
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal, Model model) throws Exception {
        User user = userService.findByUsername(principal.getName());

        if ((transferFrom.equalsIgnoreCase("primary") && user.getPrimaryAccount().getAccountBalance().compareTo(new BigDecimal(amount))<0) ||
                (transferFrom.equalsIgnoreCase("savings") && user.getSavingsAccount().getAccountBalance().compareTo(new BigDecimal(amount))<0)){
            model.addAttribute("transferFrom",transferFrom);
            model.addAttribute("transferTo",transferTo);
            model.addAttribute("amount",amount);
            model.addAttribute("error","Insufficient fund.");
            return "betweenAccounts";
        }

        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();
        transactionService.transferBetweenAccounts(transferFrom, transferTo, amount, primaryAccount, savingsAccount);
        return "redirect:/userFront";
    }

    @RequestMapping("/recipient")
    public String recipient(Model model, Principal principal){
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        Recipient recipient = new Recipient();
        model.addAttribute("recipient", recipient);
        model.addAttribute("recipientList",recipientList);
        return "recipient";
    }

    @RequestMapping(value = "/recipient/save", method = RequestMethod.POST)
    public String saveRecipient(@ModelAttribute("recipient") Recipient recipient, Principal principal){
        User user = userService.findByUsername(principal.getName());
        recipient.setUser(user);
        transactionService.saveRecipient(recipient);
        return "redirect:/transfer/recipient";
    }

    @RequestMapping("/recipient/edit")
    public String editRecipient(@RequestParam(value = "recipientName") String recipientname, Model model, Principal principal){
        Recipient recipient = transactionService.findRecipientByName(recipientname);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        model.addAttribute("recipient",recipient);
        model.addAttribute("recipientList",recipientList);
        return "recipient";
    }

    @RequestMapping("/recipient/delete")
    @Transactional
    public String deleteRecipient(@RequestParam(value = "recipientName") String recipientName, Model model, Principal principal){
        transactionService.deleteRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        Recipient recipient = new Recipient();
        model.addAttribute("recipient",recipient);
        model.addAttribute("recipientList",recipientList);
        return "recipient";
    }

    @RequestMapping("/toSomeoneElse")
    public String transferToSomeoneElse(Model model, Principal principal){
        List<Recipient> recipientList = transactionService.findRecipientList(principal);
        model.addAttribute("recipientList",recipientList);
        model.addAttribute("accountType","");
        model.addAttribute("error","");
        return "toSomeoneElse";
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.POST)
    public String doTransferToSomeoneElse(@ModelAttribute("recipientName") String recipientName,
                                          @ModelAttribute("accountType") String accountType,
                                          @ModelAttribute("amount") String amount,
                                          Principal principal, Model model){
        User user = userService.findByUsername(principal.getName());

        if ((accountType.equalsIgnoreCase("primary") && user.getPrimaryAccount().getAccountBalance().compareTo(new BigDecimal(amount))<0) ||
            (accountType.equalsIgnoreCase("savings") && user.getSavingsAccount().getAccountBalance().compareTo(new BigDecimal(amount))<0)){
            List<Recipient> recipientList = transactionService.findRecipientList(principal);
            model.addAttribute("recipientList",recipientList);
            model.addAttribute("amount",amount);
            model.addAttribute("error","Insufficient fund.");
            return "toSomeoneElse";
        }

        Recipient recipient = transactionService.findRecipientByName(recipientName);
        transactionService.transferToSomeoneElse(recipient, accountType, amount,
                user.getPrimaryAccount(), user.getSavingsAccount());
        return "redirect:/userFront";
    }
}
