package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OpenBankMetadata {
    @JsonProperty("image_URL")
    private String imageUrl;
}
