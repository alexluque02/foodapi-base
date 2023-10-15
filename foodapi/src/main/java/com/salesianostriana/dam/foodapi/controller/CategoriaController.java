package com.salesianostriana.dam.foodapi.controller;

import com.salesianostriana.dam.foodapi.dto.CategoriaDto;
import com.salesianostriana.dam.foodapi.dto.FindCategoriaDto;
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
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
            @ApiResponse(responseCode = "400 Bad Request",
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

    @Operation(summary = "Buscar una categoría por su id y número de productos que tiene")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoría",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = " {'id': 1, 'nombre': 'Alex', 'numProductos': 5}"
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría con ese id",
                    content = @Content),
    })
    @GetMapping("/{id}")
    public ResponseEntity<FindCategoriaDto> findByIdCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = servicio.findById(id);
        return categoria.map(value -> ResponseEntity.ok(
                FindCategoriaDto.of(value, servicio.countProductosByCategoria(value))
        )).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Edita una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la categoría y se ha editado con éxito",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Categoria.class)),
                            examples = {@ExampleObject(
                                    value = " {'id': 1, 'nombre': 'Alex'}"
                            )}
                    )}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ninguna categoría con ese id",
                    content = @Content),
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> editCategoria(@PathVariable Long id, @RequestBody CategoriaDto editar){
        Categoria c = servicio.edit(id, editar);
        if (c != null) {
            return ResponseEntity.ok(editar.of(c));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Borra una categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content",
                    description = "Se ha encontrado la categoría y se ha borrado con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "400 Bad Request",
                    description = "No se ha encontrado ninguna categoría con ese id",
                    content = @Content),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Categoria c = servicio.delete(id);

        if(c!=null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
