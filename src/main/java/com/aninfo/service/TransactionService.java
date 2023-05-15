package com.aninfo.service;

import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Transaction;
import com.aninfo.model.TransactionType;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getType().equals(TransactionType.DEPOSIT)) {
            Double promo = calculatePromo(transaction.getSum());
            accountService.deposit(transaction.getCbu(), transaction.getSum() + promo);
        } else if (transaction.getType().equals(TransactionType.WITHDRAWAL)) {
            accountService.withdraw(transaction.getCbu(), transaction.getSum());
        } else {
            throw new InvalidTransactionTypeException("The transaction type is not valid.");
        }
        return transactionRepository.save(transaction);
    }

    private Double calculatePromo(Double sum) {
        Double extra = Double.valueOf(0);
        if (sum >= 2000) {
            extra = sum * 0.1;
        }
        return extra > 500? 500: extra;
    }

    public Collection<Transaction> getTransactions(Long cbu) {
        return transactionRepository.getTransactionsByCbu(cbu);
    }

    public Collection<Transaction> getTransactionsById(Long id) {
        return transactionRepository.getTransactionsById(id);
    }

    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
