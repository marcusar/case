package com.example.demo.user.service;

import com.example.demo.user.domain.Type;
import com.example.demo.user.domain.UserEntity;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.resource.UserRequest;
import com.example.demo.user.resource.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private static final long ID = 1L;
    private static final String EMAIL = "example@example.exa";
    private static final Type TYPE = Type.USER;
    private static final UserRequest USER_REQUEST = new UserRequest(EMAIL, TYPE);
    private static final UserResponse USER_RESPONSE = new UserResponse(ID, EMAIL, TYPE);
    private static final UserEntity USER_ENTITY = new UserEntity().setId(ID).setEmail(EMAIL).setType(TYPE);

    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    void createUser() {
        when(mapper.toEntity(USER_REQUEST)).thenReturn(USER_ENTITY);
        when(repository.save(USER_ENTITY)).thenReturn(USER_ENTITY);
        when(mapper.toResponse(USER_ENTITY)).thenReturn(USER_RESPONSE);

        UserResponse result = service.createUser(USER_REQUEST);

        assertThat(result).isEqualTo(USER_RESPONSE);
    }

    @Test
    void getUser() {
        when(repository.findById(ID)).thenReturn(Optional.of(USER_ENTITY));
        when(mapper.toResponse(USER_ENTITY)).thenReturn(USER_RESPONSE);

        UserResponse user = service.getUser(ID);

        assertThat(user).isEqualTo(USER_RESPONSE);
    }

    @Test
    void getUsers() {
        when(repository.findAll(any(Specification.class))).thenReturn(List.of(USER_ENTITY));
        when(mapper.toResponse(USER_ENTITY)).thenReturn(USER_RESPONSE);

        List<UserResponse> users = service.getUsers(TYPE);

        assertThat(users).contains(USER_RESPONSE);
    }

}