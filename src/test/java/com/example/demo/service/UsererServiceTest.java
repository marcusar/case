package com.example.demo.service;

import com.example.demo.domain.Type;
import com.example.demo.domain.UsererEntity;
import com.example.demo.repository.UsererRepository;
import com.example.demo.resource.UsererRequest;
import com.example.demo.resource.UsererResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsererServiceTest {

    private static final long ID = 1L;
    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final UsererRequest USERER_REQUEST = new UsererRequest(EMAIL, TYPE);
    private static final UsererResponse USERER_RESPONSE = new UsererResponse(ID, EMAIL, TYPE);
    private static final UsererEntity USERER_ENTITY = new UsererEntity().setId(ID).setEmail(EMAIL).setType(TYPE);

    @Mock
    private UsererMapper mapper;
    @Mock
    private UsererRepository repository;

    @InjectMocks
    private UsererService service;

    @Test
    void createUserer() {
        when(mapper.toEntity(USERER_REQUEST)).thenReturn(USERER_ENTITY);
        when(repository.save(USERER_ENTITY)).thenReturn(USERER_ENTITY);
        when(mapper.toResponse(USERER_ENTITY)).thenReturn(USERER_RESPONSE);

        UsererResponse result = service.createUserer(USERER_REQUEST);

        assertThat(result).isEqualTo(USERER_RESPONSE);
    }

    @Test
    void getUserer() {
        when(repository.findById(ID)).thenReturn(Optional.of(USERER_ENTITY));
        when(mapper.toResponse(USERER_ENTITY)).thenReturn(USERER_RESPONSE);

        UsererResponse userer = service.getUserer(ID);

        assertThat(userer).isEqualTo(USERER_RESPONSE);
    }

    @Test
    void getUserers() {
        when(repository.findByType(TYPE)).thenReturn(List.of(USERER_ENTITY));
        when(mapper.toResponse(USERER_ENTITY)).thenReturn(USERER_RESPONSE);

        List<UsererResponse> userers = service.getUserers(TYPE);

        assertThat(userers).contains(USERER_RESPONSE);
    }

    @Test
    void getUserersEmpty() {
        when(repository.findByType(TYPE)).thenReturn(List.of());

        List<UsererResponse> userers = service.getUserers(TYPE);

        assertThat(userers).isEmpty();
    }
}