package com.example.demo.resource;

import com.example.demo.domain.Type;

public record UsererRequest(
        String email,
        Type type) {
}
