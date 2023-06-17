package com.dareen.spu;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConverterController {
    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Autowired
    public CurrencyConverterController(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public Map<String, Object> convertCurrencyFeign(@PathVariable String from,
                                                    @PathVariable String to, @PathVariable BigDecimal quantity) {
        Object response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);
        BigDecimal conversionRate = getConversionRateFromResponse(response);
        BigDecimal convertedAmount = quantity.multiply(conversionRate);

        Map<String, Object> convertedCurrency = createConvertedCurrencyMap(from, to, conversionRate, quantity, convertedAmount);

        return convertedCurrency;
    }

    private BigDecimal getConversionRateFromResponse(Object response) {
        // Implement logic to extract the conversion rate from the response
        return BigDecimal.ZERO; // Placeholder value
    }

    private Map<String, Object> createConvertedCurrencyMap(String from, String to, BigDecimal conversionRate,
                                                           BigDecimal quantity, BigDecimal convertedAmount) {
        Map<String, Object> convertedCurrency = new HashMap<>();
        convertedCurrency.put("from", from);
        convertedCurrency.put("to", to);
        convertedCurrency.put("conversionRate", conversionRate);
        convertedCurrency.put("quantity", quantity);
        convertedCurrency.put("convertedAmount", convertedAmount);
        return convertedCurrency;
    }
}
