# Cliente CRUD

Este proyecto implementa un **CRUD de Clientes** utilizando **Java 21** y **Spring Boot**, cumpliendo con los requisitos del examen teórico-práctico para programadores senior. Utiliza un `Record` para representar a los clientes, `Pattern Matching` para manejar el tipo de cliente (`VIP` o `REGULAR`), y un almacenamiento en memoria con una `List<Cliente>`. Incluye un contrato OpenAPI 2.0, pruebas unitarias, logging, y código limpio con genéricos, `var`, y lambdas.

## Características
- **Java 21**: Uso de `Record`, `Pattern Matching`, `var`, y lambdas.
- **Spring Boot**: API RESTful con endpoints para operaciones CRUD.
- **OpenAPI 2.0**: Documentación de la API en `src/main/resources/openapi.yaml`.
- **Pruebas unitarias**: Cobertura de todas las operaciones CRUD y cálculo de descuentos usando JUnit 5 y Mockito.
- **Logging**: Implementado con SLF4J para registrar operaciones.
- **Genéricos**: Uso de `GenericRepository` y `GenericService` para reusabilidad.

## Requisitos
- **Java 21** (JDK 21)
- **Maven** (3.6 o superior)
- **IDE** (opcional): IntelliJ IDEA, Eclipse, o similar
- **Herramientas para pruebas**: Postman o cURL para probar la API

## Estructura del proyecto
```
cliente-crud
├── src/main/java/com/example/cliente
│   ├── config/OpenApiConfig.java
│   ├── controller/ClienteController.java
│   ├── model/Cliente.java
│   ├── model/TipoCliente.java
│   ├── repository/GenericRepository.java
│   ├── repository/ClienteRepository.java
│   ├── service/GenericService.java
│   ├── service/ClienteService.java
│   ├── ClienteApplication.java
├── src/main/resources
│   └── openapi.yaml
├── src/test/java/com/example/cliente
│   └── service/ClienteServiceTest.java
├── pom.xml
├── README.md
```

## Instalación y ejecución
1. **Clonar el repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd cliente-crud
   ```

2. **Construir el proyecto**:
   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```
   La aplicación estará disponible en `http://localhost:8080`.

4. **Acceder a la documentación de la API**:
   - El contrato OpenAPI está en `src/main/resources/openapi.yaml`.
   - Si usas `springdoc-openapi`, accede a Swagger UI en: `http://localhost:8080/swagger-ui.html`.

## Endpoints de la API
| Método | Endpoint                     | Descripción                             |
|--------|------------------------------|-----------------------------------------|
| GET    | `/api/clientes`              | Obtiene todos los clientes              |
| GET    | `/api/clientes/{id}`         | Obtiene un cliente por ID               |
| POST   | `/api/clientes`              | Crea un nuevo cliente                   |
| PUT    | `/api/clientes/{id}`         | Actualiza un cliente por ID             |
| DELETE | `/api/clientes/{id}`         | Elimina un cliente por ID               |
| GET    | `/api/clientes/{id}/descuento` | Calcula el descuento según el tipo de cliente |

**Ejemplo de cuerpo para POST/PUT**:
```json
{
  "id": "123",
  "nombre": "Juan Pérez",
  "email": "juan@example.com",
  "edad": 30,
  "tipoCliente": "VIP"
}
```

## Pruebas unitarias
Para ejecutar las pruebas unitarias:
```bash
mvn test
```
- Los tests cubren todas las operaciones CRUD y el cálculo de descuentos (20% para VIP, 0% para REGULAR).
- Ubicación: `src/test/java/com/example/cliente/service/ClienteServiceTest.java`.

## Logging
- Se usa SLF4J para registrar operaciones CRUD y errores.
- Los logs se pueden configurar en `src/main/resources/application.properties`:
  ```properties
  logging.level.com.example.cliente=DEBUG
  ```

## Notas
- El almacenamiento es en memoria usando una `List<Cliente>`.
- El proyecto utiliza genéricos para evitar duplicación de código.
- Compatible con Java 21, aprovechando `Record` y `Pattern Matching` para un código más limpio.

## Solución de problemas
- **Error de dependencias**: Asegúrate de que el `pom.xml` incluye todas las dependencias necesarias y ejecuta `mvn clean install`.
- **API no responde**: Confirma que el puerto `8080` está libre.

Para más detalles, consulta el contrato OpenAPI en `openapi.yaml` o contacta al desarrollador.
