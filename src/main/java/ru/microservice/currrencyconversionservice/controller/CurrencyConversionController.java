package ru.microservice.currrencyconversionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.microservice.currrencyconversionservice.domain.CurrencyConversionBean;
import ru.microservice.currrencyconversionservice.proxy.CurrencyExchangeServiceProxy;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {
    @Autowired
    private CurrencyExchangeServiceProxy proxy;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable BigDecimal quantity, @PathVariable String to, @PathVariable String from) {
        CurrencyConversionBean conversion = proxy.retrieveEchangeValue(from, to);
        conversion.setQuantity(quantity);
        conversion.setTotalCalculateAmount(quantity.multiply(conversion.getConversionMultiple()));
        return conversion;
    }

//    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
//    public ResponseEntity<CurrencyConversionBean> convertCurrencyFeign(
//            @PathVariable BigDecimal quantity,
//            @PathVariable String to,
//            @PathVariable String from) {
//        Map<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class, uriVariables);
//        CurrencyConversionBean responseEntityBody = responseEntity.getBody();
//        CurrencyConversionBean conversion = CurrencyConversionBean.builder()
//                .id(responseEntityBody.getId())
//                .from(from)
//                .to(to)
//                .conversionMultiple(responseEntityBody.getConversionMultiple())
//                .quantity(quantity)
//                .totalCalculateAmount(quantity.multiply(responseEntityBody.getConversionMultiple()))
//                .port(responseEntityBody.getPort())
//                .build();
//        return ResponseEntity.ok(conversion);
//    }
}




















