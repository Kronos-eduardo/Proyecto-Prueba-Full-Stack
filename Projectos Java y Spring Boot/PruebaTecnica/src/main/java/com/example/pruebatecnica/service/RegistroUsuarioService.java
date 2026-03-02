package com.example.pruebatecnica.service;

import com.example.pruebatecnica.dto.RegistroUsuarioRequest;
import com.example.pruebatecnica.dto.RegistroUsuarioResponse;
import com.example.pruebatecnica.exception.RegistroException;
import com.example.pruebatecnica.model.RegistroUsuario;
import com.example.pruebatecnica.repository.RegistroUsuarioRepository;
import com.example.pruebatecnica.validator.RegistroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroUsuarioService {

    @Autowired
    private RegistroUsuarioRepository registroRepository;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RegistroUsuarioResponse registrarUsuario(RegistroUsuarioRequest request) {
        // Validaciones
        RegistroValidator.validarNombre(request.getNombre());
        LocalDate fechaNacimiento = RegistroValidator.validarFechaNacimiento(request.getFechaNacimiento());
        RegistroValidator.validarCorreo(request.getCorreoElectronico());
        RegistroValidator.validarSexo(request.getSexo());
        RegistroValidator.validarHobbies(request.getHobbies());

        // Validar que el correo no esté duplicado
        if (registroRepository.findByCorreoElectronico(request.getCorreoElectronico()).isPresent()) {
            throw new RegistroException("El correo electrónico ya está registrado");
        }

        // Crear la entidad
        String hobbiesStr = request.getHobbies() != null && !request.getHobbies().isEmpty()
            ? String.join(",", request.getHobbies())
            : null;

        RegistroUsuario registro = RegistroUsuario.builder()
            .nombre(request.getNombre().trim())
            .fechaNacimiento(fechaNacimiento)
            .correoElectronico(request.getCorreoElectronico().trim().toLowerCase())
            .sexo(request.getSexo())
            .hobbies(hobbiesStr)
            .build();

        RegistroUsuario registroGuardado = registroRepository.save(registro);

        return convertirAResponse(registroGuardado);
    }

    public List<RegistroUsuarioResponse> obtenerTodosRegistros() {
        return registroRepository.findAll()
            .stream()
            .map(this::convertirAResponse)
            .collect(Collectors.toList());
    }

    public RegistroUsuarioResponse obtenerRegistroPorId(Long id) {
        RegistroUsuario registro = registroRepository.findById(id)
            .orElseThrow(() -> new RegistroException("Registro no encontrado"));
        return convertirAResponse(registro);
    }

    private RegistroUsuarioResponse convertirAResponse(RegistroUsuario registro) {
        List<String> hobbiesList = null;
        if (registro.getHobbies() != null && !registro.getHobbies().isEmpty()) {
            hobbiesList = List.of(registro.getHobbies().split(","));
        }

        return RegistroUsuarioResponse.builder()
            .id(registro.getId())
            .nombre(registro.getNombre())
            .fechaNacimiento(registro.getFechaNacimiento().format(DATE_FORMATTER))
            .correoElectronico(registro.getCorreoElectronico())
            .sexo(registro.getSexo())
            .hobbies(hobbiesList)
            .fechaRegistro(registro.getFechaRegistro())
            .build();
    }
}

