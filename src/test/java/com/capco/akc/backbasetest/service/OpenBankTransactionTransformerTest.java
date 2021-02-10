package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.model.Transaction;
import com.capco.akc.backbasetest.model.openbank.OpenBankTransaction;
import com.capco.akc.backbasetest.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenBankTransactionTransformerTest {

    private OpenBankTransactionTransformer openBankTransactionTransformer;
    private String inputJson;
    private Transaction expectedTransaction;

    @BeforeEach
    public void setUp() throws Exception {
        this.openBankTransactionTransformer = new OpenBankTransactionTransformer();
        String file = "src/test/resources/saving-kids-john-single-transaction.json";
        this.inputJson = JsonUtils.readFileAsString(file);
        this.expectedTransaction = new Transaction()
                .setId("58aeed54-7042-456d-af86-f517bff5b7af")
                .setAccountId("savings-kids-john")
                .setCounterpartyAccount("savings-kids-john")
                .setCounterpartyName("ALIAS_03C57D")
                .setCounterPartyLogoPath("http://example.com")
                .setInstructedAmount(8.60)
                .setInstructedCurrency("GBP")
                .setTransactionAmount(8.60)
                .setTransactionCurrency("GBP")
                .setTransactionType("SEPA")
                .setDescription("This is a SEPA Transaction Request");
    }

    @Test
    public void apply() throws Exception {
        OpenBankTransaction openBankTransaction = new ObjectMapper().readValue(inputJson, OpenBankTransaction.class);
        Transaction actualTransaction = openBankTransactionTransformer.apply(openBankTransaction);
        assertEquals(expectedTransaction, actualTransaction);
    }

}