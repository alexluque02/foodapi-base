package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.EditProductoDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
