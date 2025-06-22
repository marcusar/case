package com.example.demo.county.resource;

import com.example.demo.county.service.CountyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CountyResource.class)
class CountyResourceTest {

    private static final String PATH = "/county/";
    private static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
    private static final String COUNTY_NAME = "Østfold";
    private static final String VALID_COUNTY_NUMBER = "31";
    private static final String INVALID_COUNTY_NUMBER = "99";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountyService countyService;

    @Test
    void getCountyName() throws Exception {
        when(countyService.fetchCountyName(VALID_COUNTY_NUMBER)).thenReturn(COUNTY_NAME);

        mockMvc.perform(get(PATH + VALID_COUNTY_NUMBER))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().string(COUNTY_NAME));
    }

    @Test
    void getCountyNameNotFound() throws Exception {
        when(countyService.fetchCountyName(INVALID_COUNTY_NUMBER)).thenReturn(null);

        mockMvc.perform(get(PATH + INVALID_COUNTY_NUMBER))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(content().string("County not found for countyNumber=" + INVALID_COUNTY_NUMBER));
    }
}