package com.example.demo.user.service;

import com.example.demo.user.domain.UserEntity;
import com.example.demo.user.resource.UserRequest;
import com.example.demo.user.resource.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserRequest userRequest) {
        return new UserEntity()
                .setEmail(userRequest.email())
                .setType(userRequest.type());
    }

    public UserResponse toResponse(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getType());
    }
}
