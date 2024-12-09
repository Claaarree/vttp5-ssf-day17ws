package sg.edu.nus.iss.vttp5a_ssf_day17ws.service;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.vttp5a_ssf_day17ws.utils.Utility;

@Service
public class CurrencyRestService {
    
    public ResponseEntity<String> getAllCountries() {
        String url = UriComponentsBuilder
                .fromUriString(Utility.currencyConverterURL)
                .pathSegment("api", "v8", "countries")
                .queryParam("apiKey", Utility.currencyConverterAPIkey)
                .toUriString();

        RequestEntity<Void> req = RequestEntity
                .get(url)
                .accept(MediaType.APPLICATION_JSON)
                .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> res = template.exchange(req, String.class);

        return res;
    }
}
