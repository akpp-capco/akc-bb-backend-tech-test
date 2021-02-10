package com.capco.akc.backbasetest.config.openbank;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Getter
public class OpenBankConfigProperties {
    @Value("${openBank.url}")
    private String openBankUrl;
}
