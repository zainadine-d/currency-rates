package rate.exchange.currencyrates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rate.exchange.currencyrates.entity.model.Currency;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

    Currency getByCurrencyCode(String currencyCode);
}
