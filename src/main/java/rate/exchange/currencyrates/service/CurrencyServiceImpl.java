package rate.exchange.currencyrates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rate.exchange.currencyrates.entity.model.Currency;
import rate.exchange.currencyrates.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;

@Service

public class CurrencyServiceImpl implements CurrencyService{


    @Autowired
    CurrencyRepository currencyRepository;


    @Override
    public List<Currency> findAll() {
        List<Currency> all = currencyRepository.findAll();
        if(all.size()==0)
            return storeCurrencies();
        return all;
    }

    /**
     * This function works like a mock... with the right condition, this function must be replaced with
     * the one available for ingestion of currencies (rate.exchange.currencyrates.core.DataIngestion.ingestCurrencyList)
     * @return
     */
    private List<Currency> storeCurrencies() {

        Currency currency= new Currency();
        currency.setCurrencyName("Mozambican Metical");
        currency.setCurrencyCode("MZN");
        Currency currency2= new Currency();
        currency2.setCurrencyName("United States Dollar");
        currency2.setCurrencyCode("USD");
        Currency currency3= new Currency();
        currency3.setCurrencyName("Euro");
        currency3.setCurrencyCode("EUR");
        Currency currency4= new Currency();
        currency4.setCurrencyName("South African Rand");
        currency4.setCurrencyCode("ZAR");
        Currency currency5= new Currency();
        currency5.setCurrencyName("British Pound Sterling");
        currency5.setCurrencyCode("GBP");
        List<Currency> list = new ArrayList<>();
        list.add(currency);
        list.add(currency2);
        list.add(currency3);
        list.add(currency4);
        list.add(currency5);
        return currencyRepository.saveAll(list);
    }
}
