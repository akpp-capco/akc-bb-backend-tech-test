package com.capco.akc.backbasetest.service;

import com.capco.akc.backbasetest.config.OpenBankConfigProperties;
import com.capco.akc.backbasetest.model.Transaction;
import com.capco.akc.backbasetest.model.openbank.OpenBankTransactionList;
import com.capco.akc.backbasetest.util.JsonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OpenBankTransactionsServiceTest {

    private static final String MOCK_URL = "http://example.com";
    private static final String BANK_ID_PARAM = "bankId";
    private static final String ACCOUNT_ID_PARAM = "accountId";
    private static final String VIEW_ID_PARAM = "viewId";

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OpenBankConfigProperties openBankConfigProperties;

    @InjectMocks
    private OpenBankTransactionsService openBankTransactionsService;

    public OpenBankTransactionsServiceTest() {
    }

    @BeforeAll
    static void init() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        Mockito.when(openBankConfigProperties.getOpenBankUrl()).thenReturn(MOCK_URL);
        this.openBankTransactionsService = new OpenBankTransactionsService(restTemplate, openBankConfigProperties);

        String transactionsUrl = MOCK_URL +
                "/banks/{" + BANK_ID_PARAM +
                "}/accounts/{" + ACCOUNT_ID_PARAM +
                "}/{" + VIEW_ID_PARAM +
                "}/transactions";

        Map<String, String> uriParams = new HashMap<>();
        uriParams.put(BANK_ID_PARAM, "rbs");
        uriParams.put(ACCOUNT_ID_PARAM, "savings-kids-john");
        uriParams.put(VIEW_ID_PARAM, "public");

        String responseFile = "src/test/resources/saving-kids-john-transactions.json";
        OpenBankTransactionList openBankTransactionList = new ObjectMapper()
                .readValue(JsonUtils.readFileAsString(responseFile), OpenBankTransactionList.class);
        Mockito.when(restTemplate.getForEntity(transactionsUrl, OpenBankTransactionList.class, uriParams))
                .thenReturn(new ResponseEntity<>(openBankTransactionList, HttpStatus.OK));
    }

    @Test
    public void getTransactions() throws Exception {
        String expectedFile = "src/test/resources/expected-transactions.json";
        List<Transaction> expectedTransactions = Arrays.asList(new ObjectMapper()
                .readValue(JsonUtils.readFileAsString(expectedFile), Transaction[].class));

        List<Transaction> actualTransactions = openBankTransactionsService.getTransactions();
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void getTransactionsByType() throws Exception {
        String expectedFile = "src/test/resources/expected-transactions.json";
        List<Transaction> expectedTransactions = Arrays.asList(new ObjectMapper()
                .readValue(JsonUtils.readFileAsString(expectedFile), Transaction[].class));
        List<Transaction> actualTransactions = openBankTransactionsService.getTransactionsByType("SEPA");
        assertEquals(expectedTransactions, actualTransactions);
    }

    @Test
    public void getTotalByTransactionType() {
        Double actualTotal = openBankTransactionsService.getTotalByTransactionType("SEPA");
        assertEquals(8.6 + 8.6 + 8.6, actualTotal);

    }
}