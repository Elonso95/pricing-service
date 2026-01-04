# Pricing Service

Microservicio de precios que, dada una fecha de aplicación, un identificador de producto y un identificador de marca,
devuelve la tarifa aplicable según las reglas de prioridad.

## Stack

- Java 17
- Spring Boot 4
- Spring Web
- Spring Data JPA
- H2 (in-memory)
- Flyway
- JUnit, Spring Boot Test
- Lombok

## Arquitectura

- Hexagonal + DDD
    - Dominio: `com.company.pricing_service.domain`
    - Aplicación: `com.company.pricing_service.application`
    - Infraestructura: `com.company.pricing_service.infrastructure`
    - API: `com.company.pricing_service.api`
- Modelos separados:
    - Dominio: `Price`
    - Persistencia: `PriceEntity`
    - API: `PriceResponse`

## Ejecución

```bash
mvn clean test
mvn spring-boot:run
