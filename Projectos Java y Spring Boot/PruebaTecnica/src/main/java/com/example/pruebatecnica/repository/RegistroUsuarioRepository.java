package com.example.pruebatecnica.repository;

import com.example.pruebatecnica.model.RegistroUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RegistroUsuarioRepository extends JpaRepository<RegistroUsuario, Long> {
    Optional<RegistroUsuario> findByCorreoElectronico(String correoElectronico);
}

