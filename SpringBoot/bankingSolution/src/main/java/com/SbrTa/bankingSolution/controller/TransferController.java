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
        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String doTransferBetweenAccounts(
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal) throws Exception {
        User user = userService.findByUsername(principal.getName());
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
}
