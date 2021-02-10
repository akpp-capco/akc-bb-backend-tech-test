package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankDetails {
    private OpenBankValue value;
    private String type;
    private String description;
}
