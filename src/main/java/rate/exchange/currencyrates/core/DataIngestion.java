package rate.exchange.currencyrates.core;

import rate.exchange.currencyrates.entity.model.ExchangeRate;

import java.util.List;

public interface DataIngestion {

    boolean ingestCurrencyList();
    List<ExchangeRate> ingestExchangeRates();
}