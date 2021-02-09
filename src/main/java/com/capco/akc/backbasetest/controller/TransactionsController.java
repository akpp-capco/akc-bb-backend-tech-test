package com.capco.akc.backbasetest.controller;

import com.capco.akc.backbasetest.model.TransactionTotal;
import com.capco.akc.backbasetest.model.Transactions;
import com.capco.akc.backbasetest.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transactions")
public class TransactionsController implements TransactionsOperations {

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {

    }

    @Override
    public ResponseEntity<List<Transactions>> getTransactions() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<List<Transactions>> getTransactionsByType(String transactionType) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

    @Override
    public ResponseEntity<TransactionTotal> getTotalByTransactionType(String transactionType) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
