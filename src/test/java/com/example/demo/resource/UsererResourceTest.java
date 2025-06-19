package com.example.demo.resource;

import com.example.demo.domain.Type;
import com.example.demo.service.UsererService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UsererResource.class)
class UsererResourceTest {

    private static final long ID = 1L;
    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final UsererRequest USERER_REQUEST = new UsererRequest(EMAIL, TYPE);
    private static final UsererResponse USERER_RESPONSE = new UsererResponse(ID, EMAIL, TYPE);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsererService service;

    @Test
    void createUserer() throws Exception {
        when(service.createUserer(USERER_REQUEST)).thenReturn(USERER_RESPONSE);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USERER_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.type").value(TYPE.name()));
    }

    @Test
    void getUserer() throws Exception {
        when(service.getUserer(ID)).thenReturn(USERER_RESPONSE);

        mockMvc.perform(get("/user/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.type").value(TYPE.name()));
    }

    @Test
    void getUserers() throws Exception {
        when(service.getUserers(TYPE)).thenReturn(List.of(USERER_RESPONSE));

        mockMvc.perform(get("/user?type=" + TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].email").value(EMAIL))
                .andExpect(jsonPath("$[0].type").value(TYPE.name()));
    }
}