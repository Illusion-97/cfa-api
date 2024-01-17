package fr.dawan.AppliCFABack.services.dg2Imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class DG2ImportTools {

    @Value("${base_url_dg2}")
    protected String baseUrl;


    @Autowired
    protected RestTemplate restTemplate;


    protected ResponseEntity<String> executeRequestOnDG2API(String email, String password, URI url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("x-auth-token", email + ":" + password);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> rep = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return rep;
    }

}
