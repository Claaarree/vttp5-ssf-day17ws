package sg.edu.nus.iss.vttp5a_ssf_day17ws.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sg.edu.nus.iss.vttp5a_ssf_day17ws.service.CurrencyRestService;

@Controller
@RequestMapping("/api/currency")
public class CurrencyRestController {
    @Autowired
    CurrencyRestService currencyRestService;
    
    @GetMapping()
    public ResponseEntity<String> getAllCurrencies() {
        ResponseEntity<String> res = currencyRestService.getAllCountries();
        return res;
    }
}
