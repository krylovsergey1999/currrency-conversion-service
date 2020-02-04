package ru.microservice.currrencyconversionservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.microservice.currrencyconversionservice.domain.CurrencyConversionBean;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionBean> convertCurrency(@PathVariable BigDecimal quantity, @PathVariable String to, @PathVariable String from) {
        CurrencyConversionBean conversion = CurrencyConversionBean.builder()
                .id(1L)
                .from(from)
                .to(to)
                .quantity(quantity)
                .conversionMultiple(BigDecimal.ONE)
                .totalCalculateAmount(quantity)
                .port(0)
                .build();
        return ResponseEntity.ok(conversion);
    }
}
