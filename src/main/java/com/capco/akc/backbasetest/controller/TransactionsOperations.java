package com.capco.akc.backbasetest.controller;

import com.capco.akc.backbasetest.model.TransactionTotal;
import com.capco.akc.backbasetest.model.Transactions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("transactions")
public interface TransactionsOperations {
    @GetMapping("")
    ResponseEntity<List<Transactions>> getTransactions();

    @GetMapping("filter/{transactionType}")
    ResponseEntity<List<Transactions>> getTransactionsByType(@PathVariable String transactionType);

    @GetMapping("filter/{transactionType}/total")
    ResponseEntity<TransactionTotal> getTotalByTransactionType(@PathVariable String transactionType);
}
