package com.example.demo.user.resource;

import com.example.demo.user.domain.Type;

public record UserRequest(
        String email,
        Type type) {
}
