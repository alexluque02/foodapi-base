# food-api
Repositorio base para el proyecto FoodApi de 2º DAM - Salesianos Triana
![Logo Food Api Created By Alex](https://github.com/alexluque02/foodapi-base/assets/114216624/3e756d77-de4a-4296-a52a-1a2f837b4170)

## Descripción del proyecto
Food-Api es una REST API desarrollada Spring Boot 3 para atender a las peticiones CRUD de entidades como Cliente, Pedido, Linea de Pedido, Producto y Categoría.

## Tecnologías utilizadas
- Java 17
- Spring Boot 3
- JPA
- Hibernate
- Documentación con Swagger API docs

## Endpoints
Tanto los cuerpos de las peticiones como sus respuestas están en formato JSON. Aquí podéis encontrar un resumen de los endpoints, para toda la información, más abajo encontraréis el enlace a la documentación a la que podréis acceder con el proyecto en marcha, además de una descarga de la colección para postman.
### Categoría
- POST ("/categoria/") - Añadir categoría
- GET ("/categoria/") - Listar categorías
- GET ("/categoria/{id}") - Obtener categoría por id
- PUT ("/categoria/{id}") - Editar categoría
- DELETE ("/categoria/{id}") - Borrar categoría

### Producto
- POST ("/producto/") - Añadir producto
- GET ("/producto/") - Listar productos
- GET ("/producto/{id}") - Obtener producto por id
- PUT ("/producto/{id}") - Editar producto
- DELETE ("/producto/{id}") - Borrar producto

### Cliente
- POST ("/cliente/") - Añadir cliente
- GET ("/cliente/") - Listar clientes
- GET ("/cliente/{id}") - Obtener cliente por id
- PUT ("/cliente/{id}") - Editar cliente
- DELETE ("/cliente/{id}") - Borrar cliente

### Pedido
- POST ("/pedido/") - Añadir pedido
- GET ("/pedido/") - Listar pedidos
- GET ("/pedido/{id}") - Obtener pedido por id
- PUT ("/pedido/{id}/del/{codLinea}") - Borrar línea de pedido
- PUT ("/pedido/{id}/mod/{codLinea}/cant/{cant}") - Modificar cantidad de productos de una línea de pedido
- PUT ("/pedido/{id}/add/{prod}/cant/{cant}") - Añadir una nueva línea de pedido al pedido
- DELETE ("/pedido/{id}") - Borrar pedido
