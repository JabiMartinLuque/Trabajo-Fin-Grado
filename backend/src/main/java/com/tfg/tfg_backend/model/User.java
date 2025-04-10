    package com.tfg.tfg_backend.model;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.Set;

    @Entity
    @Table(name = "users")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
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

        @Enumerated(EnumType.STRING)
        private Role role; // Rol del usuario (ADMIN, USER)

    }
