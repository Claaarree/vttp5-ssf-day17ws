package sg.edu.nus.iss.vttp5a_ssf_day17ws.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonNumber;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_ssf_day17ws.model.Currency;
import sg.edu.nus.iss.vttp5a_ssf_day17ws.utils.Utility;

@Service
public class CurrencyService {

    RestTemplate template = new RestTemplate();

    public List<Currency> getAllCurrencies() {
        ResponseEntity<String> res = template.exchange("http://localhost:8080/api/currency", HttpMethod.GET,
         null, String.class);

        String countriesData = res.getBody();

        List<Currency> currencyList = new ArrayList<>();

        JsonReader jReader = Json.createReader(new StringReader(countriesData));
        JsonObject jObject = jReader.readObject();
        JsonObject results = jObject.getJsonObject("results");
        Set<Entry<String, JsonValue>> countries = results.entrySet();
        for (Entry<String, JsonValue> entry : countries) {
            JsonObject c = entry.getValue().asJsonObject();
            String currencyName = c.getString("currencyName");
            String currencySymbol = c.getString("currencySymbol");
            String currencyId = c.getString("currencyId");

            Currency currency = new Currency(currencyId, currencyName, currencySymbol);
            currencyList.add(currency);
        }

        return currencyList;
    }

    // /api/v8/convert?q=USD_PHP,PHP_USD&compact=ultra&apiKey=[YOUR_API_KEY]
    public String getConversion(String from, String to) {
        String url = UriComponentsBuilder
                .fromUriString(Utility.currencyConverterURL)
                .pathSegment("api", "v8", "convert")
                .queryParam("q", from + "_" + to)
                .queryParam("compact", "ultra")
                .queryParam("apiKey", Utility.currencyConverterAPIkey)
                .toUriString();

        RequestEntity<Void> req = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        ResponseEntity<String> res = template.exchange(req, String.class);
        String conversionRateJson = res.getBody();

        JsonReader jReader = Json.createReader(new StringReader(conversionRateJson));
        JsonObject jObject = jReader.readObject();
        
        JsonNumber conversionRateJsonNumber = jObject.getJsonNumber(from + "_" + to);
        String conversionRate = conversionRateJsonNumber.toString();

        return conversionRate;
    }
    
}
