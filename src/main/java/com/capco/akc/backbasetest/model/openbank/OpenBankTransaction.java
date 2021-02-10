package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankTransaction {
    private String id;
    @JsonProperty("this_account")
    private OpenBankThisAccount thisAccount;
    @JsonProperty("other_account")
    private OpenBankOtherAccount otherAccount;
    private OpenBankDetails details;
}
