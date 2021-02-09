package com.capco.akc.backbasetest.model.openbank;

import lombok.Data;

@Data
public class OpenBankAccount {
    private String id;
    private String number;
    private OpenBankHolder holder;
    private OpenBankMetadata metadata;
}
