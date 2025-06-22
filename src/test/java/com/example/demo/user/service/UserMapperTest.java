package com.example.demo.user.service;

import com.example.demo.user.domain.Type;
import com.example.demo.user.domain.UserEntity;
import com.example.demo.user.resource.UserRequest;
import com.example.demo.user.resource.UserResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {

    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final long ID = 1L;

    private final UserMapper mapper = new UserMapper();

    @Test
    void toEntity() {
        UserRequest userRequest = new UserRequest(EMAIL, TYPE);

        UserEntity result = mapper.toEntity(userRequest);

        assertThat(result.getEmail()).isEqualTo(userRequest.email());
        assertThat(result.getType()).isEqualTo(userRequest.type());
    }

    @Test
    void toResponse() {
        UserEntity userEntity = new UserEntity().setId(ID).setEmail(EMAIL).setType(TYPE);

        UserResponse response = mapper.toResponse(userEntity);

        assertThat(response.id()).isEqualTo(ID);
        assertThat(response.email()).isEqualTo(EMAIL);
        assertThat(response.type()).isEqualTo(TYPE);

    }

    @Test
    void toResponseNull() {
        assertNull(mapper.toResponse(null));
    }
}