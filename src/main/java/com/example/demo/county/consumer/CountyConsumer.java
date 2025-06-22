package com.example.demo.county.consumer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CountyConsumer {

    private static final String PATH = "https://api.kartverket.no/kommuneinfo/v1/fylker/";

    private final RestTemplate restTemplate;

    public CountyConsumer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public CountyResponse fetchCountyInfo(String countyNumber) {
        String uri = createUri(countyNumber);
        try {
            return restTemplate.getForObject(uri, CountyResponse.class);
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    private static String createUri(String countyNumber) {
        return UriComponentsBuilder.fromHttpUrl(PATH)
                .path(countyNumber)
                .toUriString();
    }
}
