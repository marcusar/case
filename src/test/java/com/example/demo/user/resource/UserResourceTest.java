package com.example.demo.user.resource;

import com.example.demo.user.domain.Type;
import com.example.demo.user.service.UserService;
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

@WebMvcTest(UserResource.class)
class UserResourceTest {

    private static final long ID = 1L;
    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final UserRequest USER_REQUEST = new UserRequest(EMAIL, TYPE);
    private static final UserResponse USER_RESPONSE = new UserResponse(ID, EMAIL, TYPE);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService service;

    @Test
    void createUser() throws Exception {
        when(service.createUser(USER_REQUEST)).thenReturn(USER_RESPONSE);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(USER_REQUEST)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(ID))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.type").value(TYPE.name()));
    }

    @Test
    void getUser() throws Exception {
        when(service.getUser(ID)).thenReturn(USER_RESPONSE);

        mockMvc.perform(get("/user/" + ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.type").value(TYPE.name()));
    }

    @Test
    void getUsers() throws Exception {
        when(service.getUsers(TYPE)).thenReturn(List.of(USER_RESPONSE));

        mockMvc.perform(get("/user?type=" + TYPE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(ID))
                .andExpect(jsonPath("$[0].email").value(EMAIL))
                .andExpect(jsonPath("$[0].type").value(TYPE.name()));
    }
}