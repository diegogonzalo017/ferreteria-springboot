# Ferretería - Spring Boot

Aplicación web para gestionar el inventario de productos de una ferretería: alta, listado, edición y eliminación. Incluye una interfaz web con Thymeleaf y una API REST.

## Tecnologías
- Java 17
- Spring Boot (Spring Web, Spring Data JPA)
- Thymeleaf
- MySQL
- Maven

## Requisitos
- Java 17
- MySQL en `localhost:3306` con una base de datos llamada `ferreteria`
- Usuario `root` sin contraseña (credenciales de desarrollo local, se pueden cambiar en `src/main/resources/application.properties`)

## Cómo arrancarla
1. Crea la base de datos:
   ```sql
   CREATE DATABASE ferreteria;
   ```
2. En la carpeta del proyecto, ejecuta:
   ```
   ./mvnw spring-boot:run
   ```
   (en Windows: `mvnw.cmd spring-boot:run`)
3. Abre `http://localhost:8080/productos`

Las tablas se crean solas al arrancar (`spring.jpa.hibernate.ddl-auto=update`).

## Interfaz web
| Método | Ruta | Qué hace |
|--------|------|----------|
| GET | `/productos` | Lista de productos |
| GET | `/productos/nuevo` | Formulario para registrar un producto |
| POST | `/productos/crear` | Guarda un producto nuevo o editado, con validación de datos |
| GET | `/productos/editar/{id}` | Formulario para editar un producto |
| POST | `/productos/eliminar/{id}` | Elimina un producto |

## API REST
Ruta base: `/api/productos`

| Método | Ruta | Qué hace | Respuestas |
|--------|------|----------|------------|
| GET | `/api/productos` | Lista todos los productos | 200 |
| GET | `/api/productos/{id}` | Busca un producto por id | 200 / 404 |
| POST | `/api/productos` | Crea un producto (JSON en el body) | 201 / 400 |
| PUT | `/api/productos/{id}` | Edita un producto | 200 / 400 |
| DELETE | `/api/productos/{id}` | Elimina un producto | 200 / 404 |

## Estructura
```
src/main/java/com/todocodeacademy/ferreteria/
├── controller/   ProductoWebController, ProductoRestController
├── service/      IProductoService, ProductoService
├── repository/   acceso a datos con Spring Data JPA
├── model/        entidad Producto
└── dto/
src/main/resources/templates/productos/   formulario.html, lista.html
```

## Autor
Diego García - [LinkedIn](https://www.linkedin.com/in/diego-garcia-80a6a4304/)
