package com.capco.akc.backbasetest.model.openbank;

import lombok.Data;

import java.util.List;

@Data
public class OpenBankTransactionList {
    private List<OpenBankTransaction> transactions;
}
