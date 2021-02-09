package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.model.Transaction;
import com.capco.akc.backbasetest.model.openbank.OpenBankTransaction;

import java.util.function.Function;

public class OpenBankTransactionTransformer implements Function<OpenBankTransaction, Transaction> {
    @Override
    public Transaction apply(OpenBankTransaction openBankTransaction) {
        return new Transaction()
            .setId(openBankTransaction.getId())
            .setAccountId(openBankTransaction.getThisAccount().getId())
            .setCounterpartyAccount(openBankTransaction.getOtherAccount().getNumber())
            .setCounterpartyName(openBankTransaction.getOtherAccount().getHolder().getName())
            .setCounterPartyLogoPath(openBankTransaction.getOtherAccount().getMetadata().getImageUrl())
            .setInstructedAmount(new Double(openBankTransaction.getDetails().getValue().getAmount()))
            .setInstructedCurrency(openBankTransaction.getDetails().getValue().getCurrency())
            .setTransactionAmount(new Double(openBankTransaction.getDetails().getValue().getAmount()))
            .setTransactionCurrency(openBankTransaction.getDetails().getValue().getCurrency())
            .setTransactionType(openBankTransaction.getDetails().getType())
            .setDescription(openBankTransaction.getDetails().getDescription());
    }
}
