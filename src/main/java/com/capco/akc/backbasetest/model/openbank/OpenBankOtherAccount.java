package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankOtherAccount {
    private String id;
    private String number;
    private OpenBankHolder holder;
    private OpenBankMetadata metadata;
}
