package com.example.demo.county.service;

import com.example.demo.county.consumer.CountyConsumer;
import com.example.demo.county.consumer.CountyResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountyServiceTest {

    public static final String COUNTY_NUMBER = "31";
    public static final String COUNTY_NAME = "Østfold";
    @Mock
    private CountyConsumer countyConsumer;

    @InjectMocks
    private CountyService countyService;

    @Test
    void fetchCountyName() {
        when(countyConsumer.fetchCountyInfo(COUNTY_NUMBER)).thenReturn(new CountyResponse(COUNTY_NAME));

        String countyName = countyService.fetchCountyName(COUNTY_NUMBER);

        assertThat(countyName).isEqualTo(COUNTY_NAME);
    }

    @Test
    void fetchCountyNameNull() {
        when(countyConsumer.fetchCountyInfo(COUNTY_NUMBER)).thenReturn(null);

        String countyName = countyService.fetchCountyName(COUNTY_NUMBER);

        assertThat(countyName).isNull();
    }
}