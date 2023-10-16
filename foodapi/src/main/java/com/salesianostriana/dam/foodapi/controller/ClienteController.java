package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.ClienteDto;
import com.salesianostriana.dam.foodapi.dto.EditClienteDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.ClienteList;
import com.salesianostriana.dam.foodapi.servicios.ClienteServicio;
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
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
@Tag(name = "Cliente", description = "El controlador de clientes tiene diferentes métodos" +
        " para obtener información variada sobre los clientes")
public class ClienteController {

    private final ClienteServicio servicio;

    @Operation(summary = "Agrega un cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Se ha agregado un cliente con éxito", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 6,
                                        "nombre": "Alexander",
                                        "email": "correo@correo.com",
                                        "telefono": "909090",
                                        "pin": 1234
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400 Bad Request", description = "No se ha agregado ningún cliente", content = @Content)
    })
    @PostMapping("/")
    @JsonView({ClienteList.class})
    public ResponseEntity<ClienteDto> addCliente(@RequestBody EditClienteDto nuevo){
        Cliente c=servicio.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteDto.of(c));
    }

    @Operation(summary = "Lista todos los clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se han encontrado clientes", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class)), examples = {
                            @ExampleObject(value = """
                                        [
                                            {
                                                "id": 1,
                                                "nombre": "Alexander",
                                                "email": "correo@correo.com",
                                                "telefono": "909090",
                                                "pin": 1234,
                                                "numeroPedidos": 0
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Pepe",
                                                "email": "correo@correo.com",
                                                "telefono": "909090",
                                                "pin": 1234,
                                                "numeroPedidos": 1
                                            },
                                            {
                                                "id": 3,
                                                "nombre": "Juan",
                                                "email": "correo@correo.com",
                                                "telefono": "909090",
                                                "pin": 1234,
                                                "numeroPedidos": 3
                                            },
                                            {
                                                "id": 4,
                                                "nombre": "Carlos",
                                                "email": "correo@correo.com",
                                                "telefono": "909090",
                                                "pin": 1234,
                                                "numeroPedidos": 7
                                            }
                                         ]
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha encontrado ningún cliente", content = @Content)
    })
    @GetMapping("/")
    @JsonView({ClienteDetails.class})
    public ResponseEntity<List<ClienteDto>> findAllCliente(){
        List<Cliente> data = servicio.findAll();

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                data.stream()
                        .map(ClienteDto::of)
                        .toList());
    }


    @Operation(summary = "Encuentra un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha encontrado el cliente", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 2,
                                        "nombre": "Alexander",
                                        "email": "correo@correo.com",
                                        "telefono": "909090",
                                        "pin": 1234,
                                        "pedidos": []
                                    }
                                    """)
                    })
            }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha encontrado el cliente", content = @Content)
    })
    @GetMapping("/{id}")
    @JsonView({ClienteComplete.class})
    public ResponseEntity<ClienteDto> findByIdCliente(@PathVariable Long id){
        Optional<Cliente> cliente = servicio.findById(id);

        return cliente.map(value ->
                ResponseEntity.ok(ClienteDto.of(value))).orElseGet(() ->
                ResponseEntity.notFound().build());

    }

}
