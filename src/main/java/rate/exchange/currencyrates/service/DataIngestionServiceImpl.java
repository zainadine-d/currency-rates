package rate.exchange.currencyrates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rate.exchange.currencyrates.core.DataIngestion;

@Service
public class DataIngestionServiceImpl implements DataIngestionService{

    @Autowired
    DataIngestion dataIngestion;

    @Override
    @Scheduled(cron = "${job.schedule.function}")
    public void fetchData() {
        dataIngestion.ingestExchangeRates();
    }
}
