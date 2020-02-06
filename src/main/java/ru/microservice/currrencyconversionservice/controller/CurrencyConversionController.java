package ru.microservice.currrencyconversionservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.microservice.currrencyconversionservice.domain.CurrencyConversionBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionBean> convertCurrency(@PathVariable BigDecimal quantity, @PathVariable String to, @PathVariable String from) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
        CurrencyConversionBean responseEntityBody = responseEntity.getBody();
        CurrencyConversionBean conversion = CurrencyConversionBean.builder()
                .id(responseEntityBody.getId())
                .from(from)
                .to(to)
                .conversionMultiple(responseEntityBody.getConversionMultiple())
                .quantity(quantity)
                .totalCalculateAmount(quantity.multiply(responseEntityBody.getConversionMultiple()))
                .port(responseEntityBody.getPort())
                .build();
        return ResponseEntity.ok(conversion);
    }
}




















