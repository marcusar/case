package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "userer")
public class UsererEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Long getId() {
        return id;
    }

    public UsererEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsererEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Type getType() {
        return type;
    }

    public UsererEntity setType(Type type) {
        this.type = type;
        return this;
    }
}
