# 📝 Sistema de Registro de Usuarios
**Prueba Técnica - Spring Boot + React + TypeScript**

## 📋 Descripción
Sistema completo de registro de usuarios con validaciones en backend y frontend. Incluye API REST con Spring Boot y una interfaz web moderna con React + TypeScript + Bootstrap.

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java 17**
- **Spring Boot 3.5.11**
- **Spring Data JPA**
- **H2 Database** (base de datos en memoria)
- **Lombok**
- **Maven**

### Frontend
- **React 18**
- **TypeScript**
- **Vite**
- **Bootstrap 5**
- **React Icons**

## 📁 Estructura del Proyecto

```
PruebaTecnica/
├── src/                          # Backend (Spring Boot)
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/pruebatecnica/
│   │   │       ├── controller/   # Controladores REST
│   │   │       ├── dto/          # Data Transfer Objects
│   │   │       ├── exception/    # Manejo de excepciones
│   │   │       ├── model/        # Entidades JPA
│   │   │       ├── repository/   # Repositorios
│   │   │       ├── service/      # Lógica de negocio
│   │   │       └── validator/    # Validaciones
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── fontPrueba/                   # Frontend (React + TypeScript)
    ├── src/
    │   ├── App.tsx              # Componente principal
    │   ├── App.css
    │   ├── main.tsx
    │   └── assets/
    ├── package.json
    └── vite.config.ts
```

## 🚀 Instalación y Ejecución

### Requisitos Previos
- **Java 17** o superior
- **Node.js 18** o superior
- **Maven** (incluido con el proyecto)

### 1️⃣ Clonar el Repositorio
```bash
git clone https://github.com/TuUsuario/PruebaTecnica.git
cd PruebaTecnica
```

### 2️⃣ Ejecutar el Backend (Spring Boot)

#### Opción A: Con Maven Wrapper
```bash
./mvnw spring-boot:run
```

#### Opción B: Compilar y ejecutar JAR
```bash
./mvnw clean package
java -jar target/PruebaTecnica-0.0.1-SNAPSHOT.jar
```

El servidor estará disponible en: **http://localhost:8889**

### 3️⃣ Ejecutar el Frontend (React)

Abrir una nueva terminal:

```bash
cd fontPrueba
npm install
npm run dev
```

El frontend estará disponible en: **http://localhost:5173**

## 🔌 API Endpoints

### Base URL: `http://localhost:8889/api/registros`

#### 📤 Crear Registro
```http
POST /api/registros
Content-Type: application/json

{
  "nombre": "Juan Pérez",
  "fechaNacimiento": "15/03/1990",
  "correoElectronico": "juan@example.com",
  "sexo": "M",
  "hobbies": ["Deporte", "Lectura"]
}
```

**Respuesta (201 Created):**
```json
{
  "success": true,
  "message": "Registro creado exitosamente",
  "data": {
    "id": 1,
    "nombre": "Juan Pérez",
    "fechaNacimiento": "15/03/1990",
    "correoElectronico": "juan@example.com",
    "sexo": "M",
    "hobbies": ["Deporte", "Lectura"],
    "fechaRegistro": "2026-03-02"
  }
}
```

#### 📥 Obtener Todos los Registros
```http
GET /api/registros
```

**Respuesta (200 OK):**
```json
{
  "success": true,
  "message": "Registros obtenidos",
  "data": [...]
}
```

#### 🔍 Obtener Registro por ID
```http
GET /api/registros/{id}
```

## ✅ Validaciones

### Backend
- **Nombre:** Obligatorio, máximo 100 caracteres
- **Fecha de Nacimiento:** Obligatoria, formato dd/MM/yyyy, usuario debe ser mayor de edad
- **Email:** Obligatorio, formato válido, único en la base de datos
- **Sexo:** Obligatorio, solo "M" o "F"
- **Hobbies:** Al menos 1, máximo 4

### Frontend
- Validación en tiempo real
- Mensajes de error personalizados
- Deshabilitar hobbies cuando se seleccionan 4
- Feedback visual (colores, iconos)

## 🗄️ Base de Datos H2

### Acceder a la Consola H2
URL: **http://localhost:8889/h2-console**

**Credenciales:**
- JDBC URL: `jdbc:h2:mem:pruebatecnicadb`
- Username: `sa`
- Password: _(vacío)_

### Consulta de ejemplo:
```sql
SELECT * FROM registros_usuarios;
```

## 🎨 Características del Frontend

- ✅ Diseño responsive con Bootstrap 5
- ✅ Iconos visuales con React Icons
- ✅ Validación en tiempo real
- ✅ Tabla de registros con actualización automática
- ✅ Mensajes de éxito y error
- ✅ Loading states
- ✅ Integración completa con API REST

## 📦 Dependencias Principales

### Backend (pom.xml)
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

### Frontend (package.json)
```json
{
  "dependencies": {
    "react": "^18.3.1",
    "react-dom": "^18.3.1",
    "react-icons": "^5.4.0",
    "bootstrap": "^5.3.0"
  }
}
```

## 🧪 Testing

### Ejecutar Tests del Backend
```bash
./mvnw test
```

## 📝 Notas Importantes

1. **Base de Datos en Memoria:** Los datos se borran al reiniciar el servidor (configuración `spring.jpa.hibernate.ddl-auto=create-drop`)
2. **CORS:** Habilitado para desarrollo (`@CrossOrigin(origins = "*")`)
3. **Puerto Backend:** 8889
4. **Puerto Frontend:** 5173 (Vite)

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de una prueba técnica.

## 👤 Autor

**Eduardo García**

---

⭐ Si este proyecto te fue útil, dale una estrella en GitHub!

