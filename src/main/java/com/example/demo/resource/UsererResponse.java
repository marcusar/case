package com.example.demo.resource;

import com.example.demo.domain.Type;

public record UsererResponse(
        Long id,
        String email,
        Type type) {
}
