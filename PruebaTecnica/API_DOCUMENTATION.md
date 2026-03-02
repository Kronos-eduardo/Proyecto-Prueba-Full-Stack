# API de Registros de Usuarios - Documentación

## Descripción
Esta API permite registrar usuarios con información personal y guardarla en una base de datos H2.

## Endpoints

### 1. Registrar un nuevo usuario
**POST** `/api/registros`

**Headers:**
```
Content-Type: application/json
```

**Body (Request):**
```json
{
  "nombre": "Juan García",
  "fechaNacimiento": "15/03/1990",
  "correoElectronico": "juan@example.com",
  "sexo": "M",
  "hobbies": ["Lectura", "Deportes", "Música"]
}
```

**Response (201 Created):**
```json
{
  "success": true,
  "message": "Registro creado exitosamente",
  "data": {
    "id": 1,
    "nombre": "Juan García",
    "fechaNacimiento": "15/03/1990",
    "correoElectronico": "juan@example.com",
    "sexo": "M",
    "hobbies": ["Lectura", "Deportes", "Música"],
    "fechaRegistro": "2026-03-02"
  }
}
```

**Validaciones:**
- Nombre: Requerido, mínimo 2 caracteres, máximo 100
- Fecha de Nacimiento: Formato dd/mm/yyyy, debe ser una fecha válida, no puede ser futura, mínimo 13 años
- Correo: Requerido, formato válido de email
- Sexo: Requerido, solo "M" o "F"
- Hobbies: Opcional, máximo 4 opciones

**Códigos de Error:**
- 400: Error de validación
- 500: Error interno del servidor

---

### 2. Obtener todos los registros
**GET** `/api/registros`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Registros obtenidos",
  "data": [
    {
      "id": 1,
      "nombre": "Juan García",
      "fechaNacimiento": "15/03/1990",
      "correoElectronico": "juan@example.com",
      "sexo": "M",
      "hobbies": ["Lectura", "Deportes", "Música"],
      "fechaRegistro": "2026-03-02"
    },
    {
      "id": 2,
      "nombre": "María López",
      "fechaNacimiento": "22/07/1995",
      "correoElectronico": "maria@example.com",
      "sexo": "F",
      "hobbies": null,
      "fechaRegistro": "2026-03-02"
    }
  ]
}
```

---

### 3. Obtener un registro específico
**GET** `/api/registros/{id}`

**Response (200 OK):**
```json
{
  "success": true,
  "message": "Registro obtenido",
  "data": {
    "id": 1,
    "nombre": "Juan García",
    "fechaNacimiento": "15/03/1990",
    "correoElectronico": "juan@example.com",
    "sexo": "M",
    "hobbies": ["Lectura", "Deportes", "Música"],
    "fechaRegistro": "2026-03-02"
  }
}
```

**Códigos de Error:**
- 404: Registro no encontrado

---

## Hobbies Disponibles (Ejemplos)
- Lectura
- Deportes
- Música
- Viajes
- Videojuegos
- Cine
- Cocina
- Fotografía

## Configuración de la Base de Datos
- **Motor:** H2 (en memoria)
- **URL:** jdbc:h2:mem:pruebatecnicadb
- **Console H2:** http://localhost:8080/h2-console
  - Username: sa
  - Password: (vacío)
  - URL JDBC: jdbc:h2:mem:pruebatecnicadb

## Instalación y Ejecución

### Requisitos
- Java 17 o superior
- Maven 3.6 o superior

### Pasos para ejecutar
1. Navegar al directorio del proyecto:
   ```
   cd C:\Users\EDUARDO GARCIA\Documents\Projectos Java y Spring Boot\PruebaTecnica
   ```

2. Compilar y ejecutar con Maven:
   ```
   mvnw.cmd spring-boot:run
   ```

3. La aplicación estará disponible en: `http://localhost:8080`

4. Para acceder a la consola H2:
   ```
   http://localhost:8080/h2-console
   ```

## Ejemplos de uso con cURL

### Registrar un usuario:
```bash
curl -X POST http://localhost:8080/api/registros \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Carlos Sánchez",
    "fechaNacimiento": "10/05/1988",
    "correoElectronico": "carlos@example.com",
    "sexo": "M",
    "hobbies": ["Viajes", "Fotografía"]
  }'
```

### Obtener todos los registros:
```bash
curl http://localhost:8080/api/registros
```

### Obtener un registro específico:
```bash
curl http://localhost:8080/api/registros/1
```

## Estructura del Proyecto

## Notas Importantes

1. **Validaciones en el servidor:** Todas las validaciones se realizan en el servidor antes de guardar en la base de datos
2. **Hobbies opcionales:** Puedes registrar un usuario sin hobbies
3. **Correos únicos:** No se pueden registrar dos usuarios con el mismo correo electrónico
4. **Formato de fecha:** Siempre usa dd/mm/yyyy
5. **CORS habilitado:** La API permite peticiones desde cualquier origen (configurado con @CrossOrigin)


