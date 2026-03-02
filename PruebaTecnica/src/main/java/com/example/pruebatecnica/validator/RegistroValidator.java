package com.example.pruebatecnica.validator;

import com.example.pruebatecnica.exception.RegistroException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class RegistroValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@(.+)$"
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RegistroException("El nombre es requerido");
        }
        if (nombre.length() < 2) {
            throw new RegistroException("El nombre debe tener al menos 2 caracteres");
        }
        if (nombre.length() > 100) {
            throw new RegistroException("El nombre no puede exceder 100 caracteres");
        }
    }

    public static LocalDate validarFechaNacimiento(String fechaNacimientoStr) {
        if (fechaNacimientoStr == null || fechaNacimientoStr.trim().isEmpty()) {
            throw new RegistroException("La fecha de nacimiento es requerida");
        }

        try {
            LocalDate fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DATE_FORMATTER);
            LocalDate hoy = LocalDate.now();

            if (fechaNacimiento.isAfter(hoy)) {
                throw new RegistroException("La fecha de nacimiento no puede ser en el futuro");
            }

            int edad = hoy.getYear() - fechaNacimiento.getYear();
            if (hoy.getMonthValue() < fechaNacimiento.getMonthValue() ||
                (hoy.getMonthValue() == fechaNacimiento.getMonthValue() &&
                 hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth())) {
                edad--;
            }

            if (edad < 13) {
                throw new RegistroException("Debe ser mayor de 13 años para registrarse");
            }

            return fechaNacimiento;
        } catch (DateTimeParseException e) {
            throw new RegistroException("Formato de fecha inválido. Use dd/mm/yyyy");
        }
    }

    public static void validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new RegistroException("El correo electrónico es requerido");
        }

        if (!EMAIL_PATTERN.matcher(correo).matches()) {
            throw new RegistroException("Formato de correo electrónico inválido");
        }

        if (correo.length() > 255) {
            throw new RegistroException("El correo no puede exceder 255 caracteres");
        }
    }

    public static void validarSexo(String sexo) {
        if (sexo == null || sexo.trim().isEmpty()) {
            throw new RegistroException("El sexo es requerido");
        }

        if (!sexo.matches("[MF]")) {
            throw new RegistroException("El sexo debe ser M (Masculino) o F (Femenino)");
        }
    }

    public static void validarHobbies(java.util.List<String> hobbies) {
        if (hobbies == null || hobbies.isEmpty()) {
            return;
        }

        if (hobbies.size() > 4) {
            throw new RegistroException("Máximo 4 hobbies permitidos");
        }
    }
}

