package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.model.Transactions;

import java.util.List;

public interface TransactionsService {
    List<Transactions> getTransactions();
    List<Transactions> getTransactionsByType(String transactionType);
    String getTotalByTransactionType(String transactionType);
}
