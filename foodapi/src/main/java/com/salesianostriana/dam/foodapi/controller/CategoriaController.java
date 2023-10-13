package com.salesianostriana.dam.foodapi.controller;

import com.salesianostriana.dam.foodapi.dto.CategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.servicios.CategoriaServicio;
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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "El controlador de categorías tiene diferentes métodos para obtener información variada" +
        " sobre las categorías")
public class CategoriaController {

    private final CategoriaServicio servicio;

    @Operation(summary = "Agrega una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created",
                    description = "Se ha agregado una categoría con éxito",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = "{'id': 1, 'nombre': 'Alex'}"
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha agregado ninguna categoría",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<CategoriaDto> addCategoria(@RequestBody CategoriaDto nuevo){

        Categoria c = servicio.add(nuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo.of(c));
    }

    @Operation(summary = "Lista todas las categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado categorías",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = """
                                                [
                                                    {"id": 1, "nombre": "Alex"},
                                                    {"id": 2, "nombre": "Pepe"},
                                                    {"id": 3, "nombre": "Juan"}
                                                ]
                                            """
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría",
                    content = @Content),
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoriaDto>> findAllCategoria(){
        List<Categoria> data = servicio.findAll();

        if(data.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                data.stream()
                        .map(CategoriaDto::of)
                        .toList()
        );
    }

}
