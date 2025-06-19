package com.example.demo.service;

import com.example.demo.domain.Type;
import com.example.demo.domain.UsererEntity;
import com.example.demo.resource.UsererRequest;
import com.example.demo.resource.UsererResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UsererMapperTest {

    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final long ID = 1L;

    private final UsererMapper mapper = new UsererMapper();

    @Test
    void toEntity() {
        UsererRequest usererRequest = new UsererRequest(EMAIL, TYPE);

        UsererEntity result = mapper.toEntity(usererRequest);

        assertThat(result.getEmail()).isEqualTo(usererRequest.email());
        assertThat(result.getType()).isEqualTo(usererRequest.type());
    }

    @Test
    void toResponse() {
        UsererEntity usererEntity = new UsererEntity().setId(ID).setEmail(EMAIL).setType(TYPE);

        UsererResponse response = mapper.toResponse(usererEntity);

        assertThat(response.id()).isEqualTo(ID);
        assertThat(response.email()).isEqualTo(EMAIL);
        assertThat(response.type()).isEqualTo(TYPE);

    }

    @Test
    void toResponseNull() {
        assertNull(mapper.toResponse(null));
    }
}