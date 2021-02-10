package com.capco.akc.backbasetest.service.openbank;

import com.capco.akc.backbasetest.config.openbank.OpenBankConfigProperties;
import com.capco.akc.backbasetest.model.Transaction;
import com.capco.akc.backbasetest.model.openbank.OpenBankTransaction;
import com.capco.akc.backbasetest.model.openbank.OpenBankTransactionList;
import com.capco.akc.backbasetest.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OpenBankTransactionsService implements TransactionsService {

    private static final double INITIAL_AMOUNT_ZERO = 0.00;
    private static final String BANK_ID_PARAM = "bankId";
    private static final String ACCOUNT_ID_PARAM = "accountId";
    private static final String VIEW_ID_PARAM = "viewId";

    private final RestTemplate restTemplate;
    private final String openBankUrl;

    @Autowired
    public OpenBankTransactionsService(RestTemplate restTemplate,
                                       OpenBankConfigProperties openBankConfigProperties) {
        this.restTemplate = restTemplate;
        this.openBankUrl = openBankConfigProperties.getOpenBankUrl();
    }

    @Override
    public List<Transaction> getTransactions() {
        OpenBankTransactionList openBankTransactionList = getOpenBankTransactionList();
        List<OpenBankTransaction> openBankTransactions = Objects.requireNonNull(openBankTransactionList).getTransactions();
        return openBankTransactions.stream()
                .map(new OpenBankTransactionTransformer())
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getTransactionsByType(String transactionType) {
        OpenBankTransactionList openBankTransactionList = getOpenBankTransactionList();
        List<OpenBankTransaction> openBankTransactions = Objects.requireNonNull(openBankTransactionList).getTransactions();
        return openBankTransactions.stream()
                .filter(openBankTransaction -> Objects.equals(transactionType, openBankTransaction.getDetails().getType()))
                .map(new OpenBankTransactionTransformer())
                .collect(Collectors.toList());
    }

    @Override
    public Double getTotalByTransactionType(String transactionType) {
        OpenBankTransactionList openBankTransactionList = getOpenBankTransactionList();
        List<OpenBankTransaction> openBankTransactions = Objects.requireNonNull(openBankTransactionList).getTransactions();
        return openBankTransactions.stream()
                .filter(openBankTransaction -> Objects.equals(transactionType, openBankTransaction.getDetails().getType()))
                .reduce(INITIAL_AMOUNT_ZERO,
                        (subtotal, nextTransaction) -> subtotal + new Double(nextTransaction.getDetails().getValue().getAmount()),
                        Double::sum);
    }

    private OpenBankTransactionList getOpenBankTransactionList() {
        Map<String, String> uriParams = new HashMap<>();
        // URI parameters are hardcoded here because this is the only publicly available OpenBank endpoint/data that I am aware of, and should be enough for proof of concept.
        // Regardless of who is logged in to this app, the same transaction info will be retrieved.
        // Ideally, this would be parametrized based on the user's identity and further request parameters (i.e. for the account Id).
        uriParams.put(BANK_ID_PARAM, "rbs");
        uriParams.put(ACCOUNT_ID_PARAM, "savings-kids-john");
        uriParams.put(VIEW_ID_PARAM, "public");
        String transactionsUrl = openBankUrl +
                "/banks/{" + BANK_ID_PARAM +
                "}/accounts/{" + ACCOUNT_ID_PARAM +
                "}/{" + VIEW_ID_PARAM +
                "}/transactions";
        return restTemplate.getForEntity(transactionsUrl, OpenBankTransactionList.class, uriParams).getBody();
    }
}
