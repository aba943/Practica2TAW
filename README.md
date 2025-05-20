 a) Estructura del Proyecto
Verifica que la estructura de paquetes de tu código fuente sea así:

css
Copy
Edit
src/main/java/com/universidad/
│
├── controller/
│   └── MateriaController.java
│   └── EstudianteController.java
│   └── InscripcionController.java
│
├── service/
│   └── IMateriaService.java
│   └── IEstudianteService.java
│   └── IInscripcionService.java
│   └── impl/
│       └── MateriaServiceImpl.java
│       └── EstudianteServiceImpl.java
│       └── InscripcionServiceImpl.java
│
├── model/
│   └── Persona.java
│   └── Estudiante.java
│   └── Docente.java
│   └── Materia.java
│   └── Inscripcion.java
│
├── repository/
│   └── MateriaRepository.java
│   └── EstudianteRepository.java
│   └── InscripcionRepository.java
│
├── dto/
│   └── MateriaDTO.java
│   └── EstudianteDTO.java
│   └── InscripcionDTO.java
│
├── validation/
│   └── (Si tienes clases personalizadas para validación)
│
├── config/
│   └── SecurityConfig.java
│   └── JwtUtils.java
│   └── JwtAuthenticationFilter.java
│
└── UniversidadApplication.java
✅ b) Verificación de funcionalidad
Aunque te rendiste con Swagger, puedes documentar tus pruebas con Postman. Aquí te indico cómo hacerlo:

📸 Capturas sugeridas (para tu informe)
Login y obtención de token

Endpoint: POST /api/auth/login

Explicación: Se autentica un usuario y se recibe un JWT válido.

Crear Estudiante

Endpoint: POST /api/estudiantes

Explicación: Se creó correctamente un estudiante con validaciones.

Crear Materia

Endpoint: POST /api/materias

Explicación: Materia creada con código único y créditos válidos.

Crear Inscripción

Endpoint: POST /api/inscripciones

Explicación: Se vinculó exitosamente un estudiante con una materia.

GET /api/inscripciones

Muestra todas las inscripciones creadas.

DELETE /api/inscripciones/{id}

Explicación: Eliminación exitosa con respuesta 204.

Incluye las capturas de cada resultado y una explicación breve como “Resultado esperado: Código 201 Created. Se confirma que la inscripción fue creada correctamente.”

🗃️ c) Base de datos con datos de ejemplo
Incluye un archivo .sql con inserts o un .pg_dump de tu base PostgreSQL con tablas como:

sql
Copy
Edit
INSERT INTO estudiante (...) VALUES (...);
INSERT INTO materia (...) VALUES (...);
INSERT INTO inscripcion (...) VALUES (...);
O simplemente incluye una descripción del contenido de tu base de ejemplo (ej. 2 estudiantes, 3 materias, 5 inscripciones).

🧑‍💻 d) Manual Técnico
🔐 Roles y Seguridad
Usuario autenticado con rol USER o ADMIN.

Autenticación vía JWT con Authorization: Bearer <token> en headers.

🔗 Endpoints (Resumen)
Estudiantes
Método	Ruta	Descripción
POST	/api/estudiantes	Crear estudiante
GET	/api/estudiantes	Listar estudiantes
PUT	/api/estudiantes/{id}	Actualizar estudiante
DELETE	/api/estudiantes/{id}	Eliminar estudiante

Materias
Método	Ruta	Descripción
POST	/api/materias	Crear materia
GET	/api/materias	Listar materias
PUT	/api/materias/{id}	Actualizar materia
DELETE	/api/materias/{id}	Eliminar materia

Inscripciones
Método	Ruta	Descripción
POST	/api/inscripciones	Crear inscripción
GET	/api/inscripciones	Listar inscripciones
DELETE	/api/inscripciones/{id}	Eliminar inscripción

⚙️ Configuración
Incluye en tu documento el archivo application.properties, y explica brevemente:

Base de datos PostgreSQL (spring.datasource.*)

Seguridad con JWT (app.jwtSecret)

Redis (si se usa)

Spring Session

