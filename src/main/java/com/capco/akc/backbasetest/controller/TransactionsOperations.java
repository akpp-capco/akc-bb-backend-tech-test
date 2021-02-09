package com.capco.akc.backbasetest.controller;

import com.capco.akc.backbasetest.model.TransactionTotal;
import com.capco.akc.backbasetest.model.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("transactions")
public interface TransactionsOperations {
    @GetMapping("")
    List<Transaction> getTransactions();

    @GetMapping("filter/{transactionType}")
    List<Transaction> getTransactionsByType(@PathVariable String transactionType);

    @GetMapping("filter/{transactionType}/total")
    TransactionTotal getTotalByTransactionType(@PathVariable String transactionType);
}
