package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.servicios.PedidoServicio;
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
@RequestMapping("/pedido")
@Tag(name = "Pedido", description = "El controlador de pedidos tiene diferentes métodos" +
        " para obtener información variada sobre los pedidos")
public class PedidoController {

    private final PedidoServicio servicio;


    @Operation(summary = "Agrega un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201 Created", description = "Se ha creado el pedido con éxito", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 2,
                                        "fecha": "17/10/2023 19:18:24",
                                        "importe": 149.97000000000003,
                                        "cliente": {
                                            "id": 1,
                                            "nombre": "Alexander"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 2,
                                                "producto": {
                                                    "id": 1,
                                                    "nombre": "Producto de ejemplo"
                                                },
                                                "cantidad": 1,
                                                "precioUnitario": 49.99,
                                                "subtotal": 49.99
                                            },
                                            {
                                                "codLinea": 3,
                                                "producto": {
                                                    "id": 3,
                                                    "nombre": "Producto de ejemplo"
                                                },
                                                "cantidad": 2,
                                                "precioUnitario": 49.99,
                                                "subtotal": 99.98
                                            }
                                        ]
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "400 Bad Request", description = "No se ha podido crear el producto", content = @Content)
    })
    @PostMapping("/")
    @JsonView({PedidoBasic.class})
    public ResponseEntity<PedidoDto> addPedido(@RequestBody AddPedidoDto nuevo){
        Pedido p = servicio.add(nuevo);
        if(p!=null){
            return ResponseEntity.status(HttpStatus.CREATED).body(PedidoDto.of(p));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/")
    @JsonView({PedidoShort.class})
    public ResponseEntity<List<PedidoDto>> findAllPedido(){
        List<Pedido> data = servicio.findAll();

        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                data.stream()
                        .map(PedidoDto::of2)
                        .toList()
        );
    }

}
