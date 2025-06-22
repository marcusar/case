package com.example.demo.user.resource;

import com.example.demo.user.domain.Type;

public record UserResponse(
        Long id,
        String email,
        Type type) {
}
