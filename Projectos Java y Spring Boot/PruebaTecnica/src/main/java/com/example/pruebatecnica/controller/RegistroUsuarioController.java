package com.example.pruebatecnica.controller;

import com.example.pruebatecnica.dto.ApiResponse;
import com.example.pruebatecnica.dto.RegistroUsuarioRequest;
import com.example.pruebatecnica.dto.RegistroUsuarioResponse;
import com.example.pruebatecnica.exception.RegistroException;
import com.example.pruebatecnica.service.RegistroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class RegistroUsuarioController {

    @Autowired
    private RegistroUsuarioService registroService;

    @PostMapping
    public ResponseEntity<ApiResponse<RegistroUsuarioResponse>> registrarUsuario(
            @RequestBody RegistroUsuarioRequest request) {
        try {
            RegistroUsuarioResponse response = registroService.registrarUsuario(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Registro creado exitosamente", response));
        } catch (RegistroException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al registrar el usuario: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RegistroUsuarioResponse>>> obtenerTodos() {
        try {
            List<RegistroUsuarioResponse> registros = registroService.obtenerTodosRegistros();
            return ResponseEntity.ok(ApiResponse.success("Registros obtenidos", registros));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al obtener registros: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<RegistroUsuarioResponse>> obtenerPorId(
            @PathVariable Long id) {
        try {
            RegistroUsuarioResponse registro = registroService.obtenerRegistroPorId(id);
            return ResponseEntity.ok(ApiResponse.success("Registro obtenido", registro));
        } catch (RegistroException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al obtener el registro: " + e.getMessage()));
        }
    }
}

