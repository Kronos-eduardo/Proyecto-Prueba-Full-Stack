package com.example.pruebatecnica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "registros_usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, unique = true)
    private String correoElectronico;

    @Column(nullable = false)
    private String sexo;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    @PrePersist
    protected void onCreate() {
        fechaRegistro = LocalDate.now();
    }
}

