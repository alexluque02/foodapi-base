package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.EditProductoDto;
import com.salesianostriana.dam.foodapi.dto.FindCategoriaDto;
import com.salesianostriana.dam.foodapi.dto.FindProductoDto;
import com.salesianostriana.dam.foodapi.dto.ProductoDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.modelo.ProductoView;
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
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producto")
@Tag(name = "Producto", description = "El controlador de productos tiene diferentes métodos para obtener información variada" +
        " sobre los productos")
public class ProductoController {

    private final ProductoServicio servicio;

    @Operation(summary = "Agrega un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created",
                    description = "Se ha agregado un producto con éxito",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = "{'id': 1, 'nombre': 'Alex'}"
                            )}
                    )}),
            @ApiResponse(responseCode = "400 Bad Request",
                    description = "No se ha agregado ningún producto",
                    content = @Content),
    })
    @PostMapping("/")
    @JsonView({ProductoView.ProductoSinCategoria.class})
    public ResponseEntity<ProductoDto> addProducto(@RequestBody EditProductoDto nuevo){
        Producto p = servicio.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoDto.of(p));
    }


    @Operation(summary = "Buscar un producto por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el producto",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                            {
                                                "id": 1,
                                                "nombre": "Producto de ejemplo",
                                                "imagen": "imagen.jpg",
                                                "descripcion": "Este es un producto de ejemplo",
                                                "precio": 49.99,
                                                "precioOferta": 39.99,
                                                "tags": [
                                                    "electrónica",
                                                    "oferta",
                                                    "gadgets"
                                                ],
                                                "categoria": null
                                            }
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun producto con ese id",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<FindProductoDto> findByIdProducto(@PathVariable Long id){
        Optional<Producto> producto = servicio.findById(id);
        return producto.map(value -> ResponseEntity.ok(FindProductoDto.of(producto.get()))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
