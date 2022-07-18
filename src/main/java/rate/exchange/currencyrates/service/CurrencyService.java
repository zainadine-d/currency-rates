package rate.exchange.currencyrates.service;

import rate.exchange.currencyrates.entity.model.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAll();
}
