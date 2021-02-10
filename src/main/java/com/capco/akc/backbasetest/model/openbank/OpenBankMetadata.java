package com.capco.akc.backbasetest.model.openbank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenBankMetadata {
    @JsonProperty("image_URL")
    private String imageUrl;
}
