package com.example.demo.user.repository;

import com.example.demo.user.domain.Type;
import com.example.demo.user.domain.UserEntity;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserEntity> hasType(Type type) {
        return ((root, query, criteriaBuilder) -> {
            if (type == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("type"), type);
        });
    }

}
