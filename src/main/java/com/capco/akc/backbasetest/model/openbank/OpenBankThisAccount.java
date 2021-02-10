package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankThisAccount {
    private String id;
    private String number;
    private List<OpenBankHolder> holders;
    private OpenBankMetadata metadata;
}
