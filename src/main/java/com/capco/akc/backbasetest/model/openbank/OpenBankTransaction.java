package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenBankTransaction {
    private String id;
    @JsonProperty("this_account")
    private OpenBankAccount thisAccount;
    @JsonProperty("other_account")
    private OpenBankAccount otherAccount;
    private OpenBankDetails details;
}
