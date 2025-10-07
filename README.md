
# Testbcnc

Servicio REST para consulta de precios, desarrollado con Spring Boot y arquitectura hexagonal.

## Características principales
- Arquitectura hexagonal (domain, port, application, adapter/in, adapter/out)
- API REST con endpoint GET para consulta de precios
- Inicialización automática de datos en H2 usando `data.sql`
- Pruebas de integración con JUnit y MockMvc
- Tratamiento de errores con try-catch en el controlador y manejo global con `@ControllerAdvice`
- Código limpio, siguiendo principios SOLID y buenas prácticas
- Control de versiones preparado para Git

## Requisitos
- Java 8+
- Maven 3.6.3

## Instalación y ejecución
1. Clona el repositorio:
   ```sh
   git clone <URL-del-repositorio>
   cd Testbcnc
   ```
2. Compila y ejecuta los tests:
   ```sh
   mvn clean test
   ```
3. Ejecuta la aplicación:
   ```sh
   mvn spring-boot:run
   ```
4. Accede a la consola H2:
   - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuario: `sa` (sin contraseña)

## Endpoints principales
### PING
- **GET** `/api/precios/ping`
- Respuesta: `¡PONG!`

### Consulta de precio
- **GET** `/api/precios/obtenerPrecio`
- **Parámetros:**
  - `brandId` (Integer)
  - `productId` (Integer)
  - `applicationDate` (ISO 8601, ejemplo: `2020-06-14T10:00:00`)
- **Respuesta exitosa:**
  ```json
  {
    "productId": 35455,
    "brandId": 1,
    "priceList": 1,
    "startDate": "2020-06-14T00:00:00",
    "endDate": "2020-12-31T23:59:59",
    "price": 35.50,
    "curr": "EUR"
  }
  ```
- **Respuesta no encontrada:** HTTP 404
- **Errores:**
  - Si los parámetros son inválidos, HTTP 400 con mensaje claro.
  - Si ocurre un error interno, HTTP 500 con mensaje de error.

## Inicialización de datos
- Los datos de ejemplo se cargan automáticamente al iniciar la aplicación desde los archivos `schema.sql` y `data.sql`.

## Pruebas
- Ejecuta `mvn clean test` para validar los tests de integración.
- Los tests verifican la correcta respuesta del endpoint `/api/precios/obtenerPrecio` para distintos escenarios.

## Estructura del proyecto
```
src/
  main/
    java/
      com/example/Testbcnc/
        adapter/in/       # Adaptadores de entrada (REST)
            PrecioRestController.java
        adapter/out/      # Adaptadores de salida (JPA)
            PrecioRepository.java
        application/      # Casos de uso
            PrecioServiceImpl.java
        domain/           # Entidades
            Precio.java
        port/             # Interfaces de dominio
            PrecioServicePort.java
        TestbcncApplication.java
  test/
    java/
      com/example/Testbcnc/
        TestbcncApplicationTests.java
resources/
  application.properties
  data.sql
pom.xml
```

## Buenas prácticas
- Código desacoplado y testable
- Uso de DTOs y entidades separadas
- Respuestas HTTP claras y controladas
- Principios SOLID y claridad en la arquitectura
- Tratamiento de errores robusto

## Autor
- Adrián Guadalajara Abejar

## Licencia
Este proyecto es solo para fines de evaluación técnica.
