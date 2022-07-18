package rate.exchange.currencyrates.service;

import rate.exchange.currencyrates.entity.model.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> findAll();
    List<ExchangeRate> findByCurrencyCode(String code);
    ExchangeRate findByCurrencyCode(String baseCode, String targetCode);
}
