package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDto;
import com.salesianostriana.dam.foodapi.dto.producto.EditProductoDto;
import com.salesianostriana.dam.foodapi.dto.producto.FindProductoDto;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoDto;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.modelo.ProductoView.*;
import com.salesianostriana.dam.foodapi.servicios.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
@Tag(name = "Producto", description = "El controlador de productos tiene diferentes métodos para obtener información variada"
        +
        " sobre los productos")
public class ProductoController {

    private final ProductoServicio servicio;

    @Operation(summary = "Agrega un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Se ha agregado un producto con éxito", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 9,
                                        "nombre": "Burger XTREME",
                                        "imagen": "imagen.jpg",
                                        "descripcion": "Una hamburguesa muy buena",
                                        "precio": 12.99,
                                        "precioOferta": 0.0,
                                        "tags": [
                                            "queso",
                                            "ternera",
                                            "pan"
                                        ]
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400 Bad Request", description = "No se ha agregado ningún producto", content = @Content)
    })
    @PostMapping("/")
    @JsonView({ ProductoSinCategoria.class })
    public ResponseEntity<ProductoDto> addProducto(@RequestBody EditProductoDto nuevo) {
        Producto p = servicio.add(nuevo);
        if(p!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(ProductoDto.of(p));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @Operation(summary = "Buscar un producto por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha encontrado el producto", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FindProductoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 2,
                                        "nombre": "Burger Vegetariana",
                                        "imagen": "burger.jpg",
                                        "descripcion": "Burger vegetariana con guarnición de papas fritas.",
                                        "precio": 12.99,
                                        "precioOferta": 0.0,
                                        "tags": [
                                            "burger",
                                            "veggie",
                                            "papas fritas"
                                        ],
                                        "categoria": {
                                            "id": 1,
                                            "nombre": "Veggie"
                                        }
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha encontrado ningun producto con ese id", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FindProductoDto> findByIdProducto(@PathVariable Long id) {
        Optional<Producto> producto = servicio.findById(id);
        return producto.map(value -> ResponseEntity.ok(FindProductoDto.of(producto.get())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Lista todos los productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se han encontrado productos", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductoDto.class)), examples = {
                            @ExampleObject(value = """
                                        [
                                            {
                                                "id": 1,
                                                "nombre": "Ensalada",
                                                "imagen": "imagen.jpg",
                                                "descripcion": "Una rica ensalada",
                                                "precio": 9.15,
                                                "precioOferta": 4.9,
                                                "tags": [
                                                    "electrónica",
                                                    "oferta",
                                                    "gadgets"
                                                ],
                                                "categoria": "Pasta"
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Burger Vegetariana",
                                                "imagen": "burger.jpg",
                                                "descripcion": "Burger vegetariana con guarnición de papas fritas.",
                                                "precio": 12.99,
                                                "precioOferta": 0.0,
                                                "tags": [
                                                    "burger",
                                                    "veggie",
                                                    "papas fritas"
                                                ],
                                                "categoria": "Veggie"
                                            },
                                            {
                                                "id": 3,
                                                "nombre": "Spaghetti Carbonara",
                                                "imagen": "spaghetti.jpg",
                                                "descripcion": "Spaghetti con salsa carbonara y panceta.",
                                                "precio": 11.99,
                                                "precioOferta": 0.0,
                                                "tags": [
                                                    "pasta",
                                                    "italiana",
                                                    "carbonara"
                                                ],
                                                "categoria": "Pasta"
                                            }
                                        ]
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha encontrado ningún producto", content = @Content)
    })
    @GetMapping("/")
    @JsonView({ ProductoCompleto.class })
    public ResponseEntity<List<ProductoDto>> findAllProducto() {
        List<Producto> data = servicio.findAll();

        if (data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                data.stream()
                        .map(ProductoDto::of)
                        .toList());
    }

    @Operation(summary = "Editar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha editado con éxito", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FindProductoDto.class)), examples = {
                            @ExampleObject(value = """
                                        {
                                            "id": 1,
                                            "nombre": "Ensalada",
                                            "imagen": "imagen.jpg",
                                            "descripcion": "Una rica ensalada",
                                            "precio": 9.15,
                                            "precioOferta": 4.9,
                                            "tags": [
                                                "lechuga",
                                                "tomate",
                                                "básica"
                                            ],
                                            "categoria": {
                                                "id": 2,
                                                "nombre": "Pasta"
                                            }
                                        }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha encontrado ningun producto", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<FindProductoDto> editProducto(@PathVariable Long id, @RequestBody EditProductoDto editar){
        Producto p = servicio.edit(id, editar);
        if (p != null) {
            return ResponseEntity.ok(FindProductoDto.of(p));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Borra un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content",
                    description = "Se ha encontrado el producto y se han borrado tanto el producto como sus líneas de " +
                            "pedido asociadas con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404 Not Found",
                    description = "El producto no se ha encontrado",
                    content = @Content)
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProducto (@PathVariable Long id){
        Producto p = servicio.delete(id);

        if(p!=null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
