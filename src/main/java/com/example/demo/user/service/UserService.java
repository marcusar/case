package com.example.demo.user.service;

import com.example.demo.user.domain.Type;
import com.example.demo.user.domain.UserEntity;
import com.example.demo.user.repository.UserRepository;
import com.example.demo.user.repository.UserSpecification;
import com.example.demo.user.resource.UserRequest;
import com.example.demo.user.resource.UserResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        UserEntity userEntity = userMapper.toEntity(userRequest);
        UserEntity saved = userRepository.save(userEntity);
        return userMapper.toResponse(saved);
    }

    public UserResponse getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        return userMapper.toResponse(userEntity);
    }

    public List<UserResponse> getUsers(Type type) {
        Specification<UserEntity> spec = Specification.where(UserSpecification.hasType(type));
        List<UserEntity> userEntity = userRepository.findAll(spec);
        return userEntity.stream()
                .map(userMapper::toResponse)
                .toList();
    }


}
