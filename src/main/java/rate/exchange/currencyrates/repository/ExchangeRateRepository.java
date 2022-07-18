package rate.exchange.currencyrates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rate.exchange.currencyrates.entity.model.ExchangeRate;

import java.util.List;
import java.util.UUID;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, UUID> {

    List<ExchangeRate> findAllByCurrency_CurrencyCode(String code);
    ExchangeRate findAllByCurrency_CurrencyCodeAndTargetCurrency(String baseCode,String targetCode);
}
