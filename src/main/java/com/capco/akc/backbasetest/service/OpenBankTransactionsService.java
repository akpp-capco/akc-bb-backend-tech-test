package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.model.Transactions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenBankTransactionsService implements TransactionsService {
    @Override
    public List<Transactions> getTransactions() {
        return null;
    }

    @Override
    public List<Transactions> getTransactionsByType(String transactionType) {
        return null;
    }

    @Override
    public String getTotalByTransactionType(String transactionType) {
        return null;
    }
}
