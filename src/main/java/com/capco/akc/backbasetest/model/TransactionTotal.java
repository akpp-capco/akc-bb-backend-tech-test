package com.capco.akc.backbasetest.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TransactionTotal {
    private String type;
    private Double total;
}
