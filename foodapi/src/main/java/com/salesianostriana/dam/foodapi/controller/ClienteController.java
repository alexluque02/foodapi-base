package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.ClienteDto;
import com.salesianostriana.dam.foodapi.dto.EditClienteDto;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Categoria.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 6,
                                        "nombre": "Alexander",
                                        "email": "correo@correo.com",
                                        "telefono": "909090",
                                        "pin": 1234
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400 Bad Request", description = "No se ha agregado ningún cliente", content = @Content),
    })
    @PostMapping("/")
    @JsonView({ClienteList.class})
    public ResponseEntity<ClienteDto> addCliente(@RequestBody EditClienteDto nuevo){
        Cliente c=servicio.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteDto.of(c));
    }

}
