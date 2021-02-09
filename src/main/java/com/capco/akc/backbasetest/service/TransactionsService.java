package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.model.Transaction;

import java.util.List;

public interface TransactionsService {
    List<Transaction> getTransactions();
    List<Transaction> getTransactionsByType(String transactionType);
    Double getTotalByTransactionType(String transactionType);
}
