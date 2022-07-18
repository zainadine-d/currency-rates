package rate.exchange.currencyrates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rate.exchange.currencyrates.core.DataIngestion;
import rate.exchange.currencyrates.entity.model.ExchangeRate;
import rate.exchange.currencyrates.repository.ExchangeRateRepository;

import java.util.List;

@Service
public class ExchangeRateImpl implements ExchangeRateService{

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    DataIngestion dataIngestion;

    @Override
    public List<ExchangeRate> findAll() {
        List<ExchangeRate> all = exchangeRateRepository.findAll();
        if(all.size()==0)
            return dataIngestion.ingestExchangeRates();
        return all;
    }

    @Override
    public List<ExchangeRate> findByCurrencyCode(String code) {
        return exchangeRateRepository.findAllByCurrency_CurrencyCode(code);
    }

    @Override
    public ExchangeRate findByCurrencyCode(String baseCode, String targetCode) {
        return exchangeRateRepository.findAllByCurrency_CurrencyCodeAndTargetCurrency(baseCode,targetCode);
    }
}