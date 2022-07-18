package rate.exchange.currencyrates.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rate.exchange.currencyrates.service.CurrencyService;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/currencies")
    public ResponseEntity<?> getCurrencies(){
        return ResponseEntity.ok(currencyService.findAll());
    }

}
