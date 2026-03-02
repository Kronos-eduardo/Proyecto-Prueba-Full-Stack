# Prueba Técnica - Sistema de Registro de Usuarios

Sistema web para registro de usuarios con validación de datos y persistencia en base de datos.

## Requisitos

- Java 17+
- Maven 3.6+
- Node.js 16+ (para frontend)

## Ejecución Backend

### Compilar y ejecutar

En IntelliJ, abre el terminal (Alt + F12) y ejecuta:

```bash
.\mvnw.cmd clean package -DskipTests
java -jar target\PruebaTecnica-0.0.1-SNAPSHOT.jar
```

La aplicación estará disponible en: `http://localhost:8888/api/registros`

### Base de Datos

La aplicación usa H2 (base de datos en memoria). Para acceder a la consola:

```
http://localhost:8888/h2-console
```

Credenciales:
- JDBC URL: `jdbc:h2:mem:pruebatecnicadb`
- Usuario: `sa`
- Contraseña: (dejar vacío)

## API Endpoints

Ver `API_DOCUMENTATION.md` para documentación completa.

### POST /api/registros
Crear un nuevo registro de usuario.

### GET /api/registros
Obtener lista de todos los registros.

### GET /api/registros/{id}
Obtener un registro específico.

## Campos del Formulario

1. **Nombre** - Requerido, 2-100 caracteres
2. **Fecha de Nacimiento** - Requerido, formato dd/mm/yyyy, edad mínima 13 años
3. **Email** - Requerido, formato válido, único
4. **Sexo** - Requerido, M o F
5. **Hobbies** - Opcional, máximo 4

## Validaciones

- Nombre: 2-100 caracteres
- Fecha: dd/mm/yyyy válida, no futura, mínimo 13 años de edad
- Email: formato válido, máximo 255 caracteres, único
- Sexo: M (Masculino) o F (Femenino)
- Hobbies: máximo 4 opciones

## Estructura del Código

## Tecnologías

- Spring Boot 3.5.11
- Spring Data JPA
- H2 Database
- Lombok
- Maven

