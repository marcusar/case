package com.example.demo.county.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountyConsumerTest {

    private static final String COUNTY_NAME = "Østfold";
    private static final String COUNTY_NUMBER = "31";
    private static final String URL = "https://api.kartverket.no/kommuneinfo/v1/fylker/" + COUNTY_NUMBER;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CountyConsumer countyConsumer;

    @Test
    void fetchCountyInfo() {
        when(restTemplate.getForObject(URL, CountyResponse.class))
                .thenReturn(new CountyResponse(COUNTY_NAME));

        CountyResponse countyResponse = countyConsumer.fetchCountyInfo(COUNTY_NUMBER);

        assertThat(countyResponse.fylkesnavn()).isEqualTo(COUNTY_NAME);
    }

    @Test
    void fetchCountyInfoThrowsException() {
        when(restTemplate.getForObject(URL, CountyResponse.class))
                .thenThrow(new HttpClientErrorException(HttpStatusCode.valueOf(404)));

        CountyResponse countyResponse = countyConsumer.fetchCountyInfo(COUNTY_NUMBER);

        assertThat(countyResponse).isNull();
    }
}