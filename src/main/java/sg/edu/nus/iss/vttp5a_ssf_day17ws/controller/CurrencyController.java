package sg.edu.nus.iss.vttp5a_ssf_day17ws.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.vttp5a_ssf_day17ws.model.Currency;
import sg.edu.nus.iss.vttp5a_ssf_day17ws.service.CurrencyService;

@Controller
@RequestMapping()
public class CurrencyController {
    
    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
    public String landingPage(Model model) {
        List<Currency> currenciesList = currencyService.getAllCurrencies();
        model.addAttribute("currencies", currenciesList);
        return "index";
    }

    @GetMapping("/currencyConverter")
    public String showConversion(@RequestParam MultiValueMap<String, String> form, Model model) {
        // System.out.println("requestParam: " + from);

        String from = form.getFirst("from");
        String to = form.getFirst("to");
        String amount = form.getFirst("amount");

        Double conversionRate = Double.parseDouble(currencyService.getConversion(from, to));
        String convertedAmount = String.valueOf(Double.parseDouble(amount)*conversionRate);

        Currency fromCurrency = currencyService.getCurrencyFrom(from);
        Currency toCurrency = currencyService.getCurrencyTo(to);


        model.addAttribute("from", fromCurrency);
        model.addAttribute("to", toCurrency);
        model.addAttribute("amount", amount);
        model.addAttribute("convertedAmount", convertedAmount);
        
        return "conversion";
    }

}
