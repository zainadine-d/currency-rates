package rate.exchange.currencyrates.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rate.exchange.currencyrates.entity.model.Currency;
import rate.exchange.currencyrates.entity.model.ExchangeRate;
import rate.exchange.currencyrates.repository.CurrencyRepository;
import rate.exchange.currencyrates.repository.ExchangeRateRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataIngestionImpl implements DataIngestion{
    @Value("${rates.currency.service.url}")
    private String currencyUrl;

    @Value("${rates.exchange.service.url}")
    private String exchangeUrl;

    @Value("${rates.exchange.apiKey}")
    private String apiKey;

    @Autowired
    CurrencyRepository currencyRepositoryrepository;
    @Autowired
    ExchangeRateRepository exchangeRateRepository;


    /**
    Due to limitations of the amount of request per day, this function wont be executed but its here in case the
     API key used has no limitation...
     */
    @Override
    public boolean ingestCurrencyList() {
        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> objectHttpEntity = new HttpEntity<>(null, headers);
        headers.add("apikey", apiKey);
        ResponseEntity<String> exchange = restTemplate.exchange(currencyUrl, HttpMethod.GET, objectHttpEntity, String.class);

        try {
            Map<String, Object> map = objectMapper.readValue(exchange.getBody(), Map.class);
            Map<String, String> listItem =
                    objectMapper.readValue(objectMapper.writeValueAsString(map.get("symbols")), Map.class);

            for (String key : listItem.keySet()) {
                Currency currency = new Currency();
                currency.setCurrencyCode(key);
                currency.setCurrencyName(listItem.get(key));
                currencyRepositoryrepository.save(currency);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    @Override
    public List<ExchangeRate> ingestExchangeRates() {
        eraseData();

        ObjectMapper objectMapper = new ObjectMapper();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> objectHttpEntity = new HttpEntity<>(null, headers);
        headers.add("apikey", apiKey);

        List<Currency> all = currencyRepositoryrepository.findAll();
        List<ExchangeRate> exchangeRates=new ArrayList<>();
        all.stream().forEach(currency -> {
            ResponseEntity<String> exchange = restTemplate.exchange(exchangeUrl + currency.getCurrencyCode(), HttpMethod.GET, objectHttpEntity, String.class);

            try {
                Map<String, Object> map = objectMapper.readValue(exchange.getBody(), Map.class);
                Map<String, Object> listItem =
                        objectMapper.readValue(objectMapper.writeValueAsString(map.get("rates")), Map.class);

                for (String key : listItem.keySet()) {
                    ExchangeRate exchangeRate = new ExchangeRate();
                    exchangeRate.setCurrency(currency);
                    exchangeRate.setValue(Double.parseDouble(listItem.get(key).toString()));
                    exchangeRate.setTargetCurrency(key);
                    exchangeRates.add(exchangeRate);
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return exchangeRateRepository.saveAll(exchangeRates);
    }

    private void eraseData(){
        exchangeRateRepository.deleteAll();
    }
}
