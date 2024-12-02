package com.example.pdsbackend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column() // Asegura que siempre se proporcione un valor.
    private String role = "EVALUATOR"; // Valor por defecto.

    // Constructor con todos los campos.
    public User(Long id, String username, String password, String email, LocalDateTime createdAt, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
    }

    // Constructor sin el campo 'role', se usará el valor por defecto.
    public User(Long id, String username, String password, String email, LocalDateTime createdAt) {
        this(id, username, password, email, createdAt, "EVALUATOR");
    }

    public User() {}

    public User(User user){
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), user.getCreatedAt(), user.getRole());
    }
}
