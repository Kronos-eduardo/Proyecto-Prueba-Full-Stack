package com.example.pruebatecnica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistroUsuarioResponse {

    private Long id;
    private String nombre;
    private String fechaNacimiento;
    private String correoElectronico;
    private String sexo;
    private List<String> hobbies;
    private LocalDate fechaRegistro;
}

