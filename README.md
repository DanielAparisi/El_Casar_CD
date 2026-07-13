# El Casar CD — Web del equipo

Página web de un equipo de fútbol. Es un **proyecto de aprendizaje** cuyo objetivo es practicar:

- **Programación Orientada a Objetos (POO)** con Java
- **Spring Boot** (controladores, plantillas Thymeleaf, inyección de dependencias)
- **PostgreSQL** (persistencia de datos con Spring Data JPA — pendiente de añadir)

## Stack

| Tecnología | Uso |
| --- | --- |
| Java 21 | Lenguaje principal |
| Spring Boot | Framework web |
| Thymeleaf | Plantillas HTML del lado del servidor |
| Maven | Gestión de dependencias y build |
| PostgreSQL | Base de datos (próximamente) |

## Estructura del proyecto

```text
src/main/java/com/ElCasarCD/web/
├── WebApplication.java        # Punto de entrada de Spring Boot
├── controller/                # Controladores web (rutas HTTP)
└── model/                     # Clases del dominio (Jugador, Equipo...)

src/main/resources/
├── templates/                 # Plantillas Thymeleaf (HTML)
├── static/css/                # Estilos
└── static/js/                 # JavaScript del cliente
```

## Cómo ejecutar

```bash
./mvnw spring-boot:run
```

La web queda disponible en `http://localhost:8080`.
