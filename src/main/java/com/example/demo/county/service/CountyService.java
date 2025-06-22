package com.example.demo.county.service;

import com.example.demo.county.consumer.CountyConsumer;
import com.example.demo.county.consumer.CountyResponse;
import org.springframework.stereotype.Service;

@Service
public class CountyService {

    private final CountyConsumer countyConsumer;

    public CountyService(CountyConsumer countyConsumer) {
        this.countyConsumer = countyConsumer;
    }

    public String fetchCountyName(String countyNumber) {
        CountyResponse countyResponse = countyConsumer.fetchCountyInfo(countyNumber);
        return countyResponse != null ? countyResponse.fylkesnavn() : null;
    }
}
