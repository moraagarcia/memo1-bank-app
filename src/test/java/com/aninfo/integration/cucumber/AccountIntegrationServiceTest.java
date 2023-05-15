package com.aninfo.integration.cucumber;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    Account createAccount(Double balance) {
        return accountService.createAccount(new Account(balance));
    }

    Transaction withdraw(Account account, Double sum) {
        Transaction withdrawal = new Transaction(account.getCbu(), sum, TransactionType.WITHDRAWAL);
        return transactionService.createTransaction(withdrawal);
    }

    Transaction deposit(Account account, Double sum) {
        Transaction deposit = new Transaction(account.getCbu(), sum, TransactionType.DEPOSIT);
        return transactionService.createTransaction(deposit);
    }

}
