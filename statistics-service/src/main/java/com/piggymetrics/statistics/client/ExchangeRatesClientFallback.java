package com.piggymetrics.statistics.client;

import com.piggymetrics.statistics.domain.Currency;
import com.piggymetrics.statistics.domain.ExchangeRatesContainer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExchangeRatesClientFallback implements ExchangeRatesClient {

    @Override
    public ExchangeRatesContainer getRates(Currency base) {
        ExchangeRatesContainer container = new ExchangeRatesContainer();
        container.setBase(Currency.getBase());
        Map<String, BigDecimal> rates = new HashMap<String, BigDecimal>();
        rates.put("EUR", new BigDecimal("1.09"));
        rates.put("RUB", new BigDecimal("0.01"));
        container.setRates(rates);
        return container;
    }
}
