package rate.exchange.currencyrates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rate.exchange.currencyrates.service.ExchangeRateService;

@RestController
@RequestMapping("/rates")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @GetMapping()
    public ResponseEntity<?> getRates(){
        return ResponseEntity.ok(exchangeRateService.findAll());
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getRatesByCode(@PathVariable("code") String code){
        return ResponseEntity.ok(this.exchangeRateService.findByCurrencyCode(code));
    }

    @GetMapping("/{baseCode}/{targetCode}")
    public ResponseEntity<?> getRatesByCode(@PathVariable("baseCode") String baseCode,
                                            @PathVariable("targetCode") String targetCode){
        return ResponseEntity.ok(this.exchangeRateService.findByCurrencyCode(baseCode,targetCode));
    }
}