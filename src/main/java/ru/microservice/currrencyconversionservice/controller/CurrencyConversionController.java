package ru.microservice.currrencyconversionservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.microservice.currrencyconversionservice.domain.CurrencyConversionBean;
import ru.microservice.currrencyconversionservice.proxy.CurrencyExchangeServiceProxy;

import java.math.BigDecimal;

@Slf4j
@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable BigDecimal quantity, @PathVariable String to, @PathVariable String from) {
        CurrencyConversionBean conversion = proxy.retrieveEchangeValue(from, to);
        conversion.setQuantity(quantity);
        conversion.setTotalCalculateAmount(quantity.multiply(conversion.getConversionMultiple()));
        log.info(conversion.toString());
        return conversion;
    }
}




















