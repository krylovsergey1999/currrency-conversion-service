package ru.microservice.currrencyconversionservice.domain;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CurrencyConversionBean {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal quantity;
    private BigDecimal totalCalculateAmount;
    private int port;
}
