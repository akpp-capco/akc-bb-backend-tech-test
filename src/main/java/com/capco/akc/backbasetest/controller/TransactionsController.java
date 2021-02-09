package com.capco.akc.backbasetest.controller;

import com.capco.akc.backbasetest.model.TransactionTotal;
import com.capco.akc.backbasetest.model.Transaction;
import com.capco.akc.backbasetest.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionsController implements TransactionsOperations {

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionsService.getTransactions();
    }

    @Override
    public List<Transaction> getTransactionsByType(String transactionType) {
        return transactionsService.getTransactionsByType(transactionType);
    }

    @Override
    public TransactionTotal getTotalByTransactionType(String transactionType) {
        return new TransactionTotal()
                .setType(transactionType)
                .setTotal(transactionsService.getTotalByTransactionType(transactionType));
    }
}
