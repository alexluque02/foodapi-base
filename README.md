# food-api
Repositorio base para el proyecto FoodApi de 2º DAM - Salesianos Triana
![Logo Food Api Created By Alex](https://github.com/alexluque02/foodapi-base/assets/114216624/3e756d77-de4a-4296-a52a-1a2f837b4170)

## Descripción del proyecto
Food-Api es una REST API desarrollada en Spring Boot 3 que proporciona endpoints para gestionar las entidades relacionadas con un sistema de pedidos de comida. A través de esta API, puedes realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en entidades como **Cliente**, **Pedido**, **Línea de Pedido**, **Producto** y **Categoría**.

## Tecnologías utilizadas
- `Java 17`
- `Spring Boot 3`
- `JPA`
- `Hibernate`
- `Documentación con Swagger API docs`

## Cómo utilizar la API REST
Para poder ponerla en marcha, el usuario deberá disponer de Spring Boot 3 con JDK 17 instalado y al iniciar la API REST se podrán probar todos los Endpoints en Postman.

### Ejecutar la aplicación
Para compilar y ejecutar la API REST se deben ejecutar los siguientes comandos en el terminal:
```
mvn install
```
Esto compilará el proyecto y generará las dependencias necesarias. Después para ejecutar la API:
```
mvn spring-boot:run
```
Esto abrirá un servidor de Spring Boot ej el puesto 8080 por defecto.

## Colección de Postman
- **[Descargar Colección de Postman](FoodApi.postman_collection.json)**
- **[Descargar Colección de Postman importada desde Swagger](FoodApiImportadaSwagger.postman_collection)**

## Documentación
La documentación de los Endpoints se podrá consultar a través de http://localhost:8080/swagger-ui/index.html una vez desplegado el proyecto en el puerto 8080.

## Endpoints
Tanto los cuerpos de las peticiones como sus respuestas están en formato JSON. Aquí podéis encontrar un resumen de los endpoints, para toda la información, más abajo encontraréis el enlace a la documentación a la que podréis acceder con el proyecto en marcha, además de una descarga de la colección para Postman.

### Categoría
- `POST ("/categoria/")` - Añadir categoría
- `GET ("/categoria/")` - Listar categorías
- `GET ("/categoria/{id}")` - Obtener categoría por id
- `PUT ("/categoria/{id}")` - Editar categoría
- `DELETE ("/categoria/{id}")` - Borrar categoría

### Producto
- `POST ("/producto/")` - Añadir producto
- `GET ("/producto/")` - Listar productos
- `GET ("/producto/{id}")` - Obtener producto por id
- `PUT ("/producto/{id}")` - Editar producto
- `DELETE ("/producto/{id}")` - Borrar producto

### Cliente
- `POST ("/cliente/")` - Añadir cliente
- `GET ("/cliente/")` - Listar clientes
- `GET ("/cliente/{id}")` - Obtener cliente por id
- `PUT ("/cliente/{id}")` - Editar cliente
- `DELETE ("/cliente/{id}")` - Borrar cliente

### Pedido
- `POST ("/pedido/")` - Añadir pedido
- `GET ("/pedido/")` - Listar pedidos
- `GET ("/pedido/{id}")` - Obtener pedido por id
- `PUT ("/pedido/{id}/del/{codLinea}")` - Borrar línea de pedido
- `PUT ("/pedido/{id}/mod/{codLinea}/cant/{cant}")` - Modificar cantidad de productos de una línea de pedido
- `PUT ("/pedido/{id}/add/{prod}/cant/{cant}")` - Añadir una nueva línea de pedido al pedido
- `DELETE ("/pedido/{id}")` - Borrar pedido
