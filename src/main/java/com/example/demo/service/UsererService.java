package com.example.demo.service;

import com.example.demo.domain.Type;
import com.example.demo.domain.UsererEntity;
import com.example.demo.repository.UsererRepository;
import com.example.demo.resource.UsererRequest;
import com.example.demo.resource.UsererResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsererService {

    private final UsererMapper usererMapper;
    private final UsererRepository usererRepository;

    public UsererService(UsererMapper usererMapper, UsererRepository usererRepository) {
        this.usererMapper = usererMapper;
        this.usererRepository = usererRepository;
    }

    public UsererResponse createUserer(UsererRequest usererRequest) {
        UsererEntity usererEntity = usererMapper.toEntity(usererRequest);
        UsererEntity saved = usererRepository.save(usererEntity);
        return usererMapper.toResponse(saved);
    }

    public UsererResponse getUserer(Long id) {
        UsererEntity usererEntity = usererRepository.findById(id).orElse(null);
        return usererMapper.toResponse(usererEntity);
    }

    public List<UsererResponse> getUserers(Type type) {
        List<UsererEntity> usererEntity = usererRepository.findByType(type);
        return usererEntity.stream()
                .map(usererMapper::toResponse)
                .toList();
    }


}
