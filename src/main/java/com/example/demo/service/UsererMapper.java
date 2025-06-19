package com.example.demo.service;

import com.example.demo.domain.UsererEntity;
import com.example.demo.resource.UsererRequest;
import com.example.demo.resource.UsererResponse;
import org.springframework.stereotype.Component;

@Component
public class UsererMapper {

    public UsererEntity toEntity(UsererRequest usererRequest) {
        return new UsererEntity()
                .setEmail(usererRequest.email())
                .setType(usererRequest.type());
    }

    public UsererResponse toResponse(UsererEntity usererEntity) {
        if (usererEntity == null) {
            return null;
        }
        return new UsererResponse(
                usererEntity.getId(),
                usererEntity.getEmail(),
                usererEntity.getType());
    }
}
