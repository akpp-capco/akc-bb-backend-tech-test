package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankHolder {
    private String name;
}
