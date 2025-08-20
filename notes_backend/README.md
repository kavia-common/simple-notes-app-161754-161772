# Notes Backend

Spring Boot REST API for managing notes (create, read, update, delete).

## Run
- Using Gradle Wrapper:
  - ./gradlew bootRun

## Default Database
- H2 in-memory database (can be overridden using env vars):
  - SPRING_DATASOURCE_URL
  - SPRING_DATASOURCE_DRIVER
  - SPRING_DATASOURCE_USERNAME
  - SPRING_DATASOURCE_PASSWORD
  - SPRING_JPA_DATABASE_PLATFORM
  - SPRING_JPA_HIBERNATE_DDL_AUTO
  - SPRING_JPA_SHOW_SQL

## Endpoints
- GET / -> Welcome
- GET /health -> Health check
- GET /docs -> Swagger UI redirect (/swagger-ui.html)

Notes CRUD:
- POST /api/notes
- GET /api/notes
- GET /api/notes/{id}
- PUT /api/notes/{id}
- DELETE /api/notes/{id}

## OpenAPI
- OpenAPI JSON: /api-docs
- Swagger UI: /swagger-ui.html
