package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDetailsDto;
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
import java.util.Optional;

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
            @ApiResponse(responseCode = "400 Bad Request", description = "No se ha podido crear el pedido", content = @Content)
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

    @Operation(summary = "Lista todos los pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se han encontrado pedidos", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)), examples = {
                            @ExampleObject(value = """
                                    [
                                         {
                                             "id": 1,
                                             "fecha": "17/10/2023 19:22:34",
                                             "importe": 149.97000000000003,
                                             "cliente": {
                                                 "id": 1,
                                                 "nombre": "Alexander"
                                             },
                                             "cantidadProductosDiferentes": 2,
                                             "cantidadTotal": 3
                                         },
                                         {
                                             "id": 2,
                                             "fecha": "17/10/2023 19:22:36",
                                             "importe": 149.97000000000003,
                                             "cliente": {
                                                 "id": 1,
                                                 "nombre": "Alexander"
                                             },
                                             "cantidadProductosDiferentes": 2,
                                             "cantidadTotal": 3
                                         }
                                     ]
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha podido encontrar ningún pedido", content = @Content)
    })
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

    @Operation(summary = "Busca un pedido por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se han encontrado el pedido", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fecha": "17/10/2023 19:51:01",
                                        "importe": 149.97000000000003,
                                        "cliente": {
                                            "id": 1,
                                            "nombre": "Alexander",
                                            "email": "correo@correo.com",
                                            "telefono": "909090"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 1,
                                                "producto": {
                                                    "id": 1,
                                                    "nombre": "Producto de ejemplo",
                                                    "categoria": "Sin categoría"
                                                },
                                                "cantidad": 1,
                                                "precioUnitario": 49.99,
                                                "subtotal": 49.99
                                            },
                                            {
                                                "codLinea": 2,
                                                "producto": {
                                                    "id": 3,
                                                    "nombre": "Producto de ejemplo",
                                                    "categoria": "Sin categoría"
                                                },
                                                "cantidad": 2,
                                                "precioUnitario": 49.99,
                                                "subtotal": 99.98
                                            }
                                        ]
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha podido encontrar el pedido", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetailsDto> findByIdPedido(@PathVariable Long id){
        Optional<Pedido> pedido = servicio.findById(id);

        return pedido.map(value -> ResponseEntity.ok(PedidoDetailsDto.of(value))).orElseGet
                (() -> ResponseEntity.notFound().build());

    }

    @Operation(summary = "Elimina una línea de pedido del pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha eliminado la línea de pedido", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fecha": "18/10/2023 09:48:18",
                                        "importe": 49.99,
                                        "cliente": {
                                            "id": 1,
                                            "nombre": "Alexander"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 1,
                                                "producto": {
                                                    "id": 1,
                                                    "nombre": "Producto de ejemplo"
                                                },
                                                "cantidad": 1,
                                                "precioUnitario": 49.99,
                                                "subtotal": 49.99
                                            }
                                        ]
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha podido eliminar la línea de pedido", content = @Content)
    })
    @PutMapping("/{id}/del/{codLinea}")
    @JsonView({PedidoBasic.class})
    public ResponseEntity<PedidoDto> deleteLineaPedido(@PathVariable Long id, @PathVariable Long codLinea){
        Pedido pedido = servicio.deleteLinea(id, codLinea);

        if(pedido!=null){
            return ResponseEntity.ok(PedidoDto.of(pedido));
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Modifica la cantidad de productos comprados en una línea de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha modificado la cantidad con éxito", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Pedido.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                         "id": 1,
                                         "fecha": "18/10/2023 10:15:10",
                                         "importe": 199.96,
                                         "cliente": {
                                             "id": 1,
                                             "nombre": "Alexander"
                                         },
                                         "lineasPedido": [
                                             {
                                                 "codLinea": 1,
                                                 "producto": {
                                                     "id": 1,
                                                     "nombre": "Producto de ejemplo"
                                                 },
                                                 "cantidad": 4,
                                                 "precioUnitario": 49.99,
                                                 "subtotal": 199.96
                                             }
                                         ]
                                     }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha podido modificar la cantidad de productos de la línea de pedido", content = @Content)
    })
    @PutMapping("/{id}/mod/{codLinea}/cant/{cant}")
    @JsonView({PedidoBasic.class})
    public ResponseEntity<PedidoDto> modifyCantidad(@PathVariable Long id, @PathVariable Long codLinea,
                                                    @PathVariable int cant){

        Pedido pedido = servicio.modifyCant(id, codLinea, cant);

        if(pedido!=null){
            return ResponseEntity.ok(PedidoDto.of(pedido));
        }

        return ResponseEntity.notFound().build();

    }

}
