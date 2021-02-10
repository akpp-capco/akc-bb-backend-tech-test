package com.capco.akc.backbasetest.controller;

import com.capco.akc.backbasetest.model.TransactionTotal;
import com.capco.akc.backbasetest.service.openbank.OpenBankTransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransactionsControllerTest {

    private static final String TEST_TRANSACTION_TYPE = "SEPA";
    private static final double EXPECTED_TOTAL = 8.6 + 8.6 + 8.6;

    @Mock
    private OpenBankTransactionsService openBankTransactionsService;

    @InjectMocks
    private TransactionsController transactionsController;

    @BeforeEach
    public void setUp() {
        Mockito.when(openBankTransactionsService.getTotalByTransactionType(TEST_TRANSACTION_TYPE)).thenReturn(EXPECTED_TOTAL);
        this.transactionsController = new TransactionsController(openBankTransactionsService);
    }

    // getTotalByTransactionType() gets a test because it is the only endpoint in this controller with any transformations on the return value from the service layer.
    @Test
    public void getTotalByTransactionType() {
        TransactionTotal actualTransactionTotal = transactionsController.getTotalByTransactionType(TEST_TRANSACTION_TYPE);
        TransactionTotal expectedTransactionTotal = new TransactionTotal().setType(TEST_TRANSACTION_TYPE).setTotal(EXPECTED_TOTAL);
        assertEquals(expectedTransactionTotal, actualTransactionTotal);
    }
}