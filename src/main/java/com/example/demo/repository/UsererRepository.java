package com.example.demo.repository;

import com.example.demo.domain.Type;
import com.example.demo.domain.UsererEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsererRepository extends JpaRepository<UsererEntity, Long> {

    List<UsererEntity> findByType(Type type);
}
