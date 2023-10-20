package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDetailsDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;
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
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 4,
                                        "fecha": "19/10/2023 16:53:40",
                                        "importe": 33.97,
                                        "cliente": {
                                            "id": 1,
                                            "nombre": "Juan Pérez"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 5,
                                                "producto": {
                                                    "id": 1,
                                                    "nombre": "Ensalada de Espinacas"
                                                },
                                                "cantidad": 1,
                                                "precioUnitario": 9.99,
                                                "subtotal": 9.99
                                            },
                                            {
                                                "codLinea": 6,
                                                "producto": {
                                                    "id": 3,
                                                    "nombre": "Spaghetti Carbonara"
                                                },
                                                "cantidad": 2,
                                                "precioUnitario": 11.99,
                                                "subtotal": 23.98
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
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class)), examples = {
                            @ExampleObject(value = """
                                    [
                                          {
                                              "id": 1,
                                              "fecha": "19/10/2023 16:53:24",
                                              "importe": 17.98,
                                              "cliente": {
                                                  "id": 1,
                                                  "nombre": "Juan Pérez"
                                              },
                                              "cantidadProductosDiferentes": 1,
                                              "cantidadTotal": 2
                                          },
                                          {
                                              "id": 2,
                                              "fecha": "19/10/2023 16:53:24",
                                              "importe": 54.95,
                                              "cliente": {
                                                  "id": 3,
                                                  "nombre": "Pedro Gómez"
                                              },
                                              "cantidadProductosDiferentes": 2,
                                              "cantidadTotal": 5
                                          },
                                          {
                                              "id": 3,
                                              "fecha": "19/10/2023 16:53:24",
                                              "importe": 9.99,
                                              "cliente": {
                                                  "id": 2,
                                                  "nombre": "María López"
                                              },
                                              "cantidadProductosDiferentes": 1,
                                              "cantidadTotal": 1
                                          },
                                          {
                                              "id": 4,
                                              "fecha": "19/10/2023 16:53:40",
                                              "importe": 33.97,
                                              "cliente": {
                                                  "id": 1,
                                                  "nombre": "Juan Pérez"
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
                        .map(PedidoDto::ofAuxiliar)
                        .toList()
        );
    }

    @Operation(summary = "Busca un pedido por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se han encontrado el pedido", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDetailsDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 3,
                                        "fecha": "19/10/2023 16:53:24",
                                        "importe": 9.99,
                                        "cliente": {
                                            "id": 2,
                                            "nombre": "María López",
                                            "email": "maria@example.com",
                                            "telefono": "987-654-3210"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 4,
                                                "producto": {
                                                    "id": 1,
                                                    "nombre": "Ensalada de Espinacas",
                                                    "categoria": "Veggie"
                                                },
                                                "cantidad": 1,
                                                "precioUnitario": 9.99,
                                                "subtotal": 9.99
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
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                         "id": 2,
                                         "fecha": "19/10/2023 16:53:24",
                                         "importe": 21.98,
                                         "cliente": {
                                             "id": 3,
                                             "nombre": "Pedro Gómez"
                                         },
                                         "lineasPedido": [
                                             {
                                                 "codLinea": 3,
                                                 "producto": {
                                                     "id": 5,
                                                     "nombre": "Burger BBQ"
                                                 },
                                                 "cantidad": 2,
                                                 "precioUnitario": 10.99,
                                                 "subtotal": 21.98
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
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 2,
                                        "fecha": "19/10/2023 16:53:24",
                                        "importe": 43.96,
                                        "cliente": {
                                            "id": 3,
                                            "nombre": "Pedro Gómez"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 3,
                                                "producto": {
                                                    "id": 5,
                                                    "nombre": "Burger BBQ"
                                                },
                                                "cantidad": 4,
                                                "precioUnitario": 10.99,
                                                "subtotal": 43.96
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

    @Operation(summary = "Añade una línea de pedido nueva a un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200 OK", description = "Se ha agregado con éxito la línea de pedido", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PedidoDto.class)), examples = {
                            @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "fecha": "19/10/2023 16:53:24",
                                        "importe": 77.94,
                                        "cliente": {
                                            "id": 1,
                                            "nombre": "Juan Pérez"
                                        },
                                        "lineasPedido": [
                                            {
                                                "codLinea": 7,
                                                "producto": {
                                                    "id": 2,
                                                    "nombre": "Burger Vegetariana"
                                                },
                                                "cantidad": 6,
                                                "precioUnitario": 12.99,
                                                "subtotal": 77.94
                                            }
                                        ]
                                    }
                                    """) }) }),
            @ApiResponse(responseCode = "404 Not Found", description = "No se ha podido agregar la línea de pedido", content = @Content)
    })
    @PutMapping("/{id}/add/{prod}/cant/{cant}")
    @JsonView({PedidoBasic.class})
    public ResponseEntity<PedidoDto> modifyPedidoByAddingProducto(@PathVariable Long id, @PathVariable Long prod,
                                                    @PathVariable int cant){

        Pedido pedido = servicio.modifyPedidoProdAndCant(id, prod, cant);

        if(pedido!=null){
            return ResponseEntity.ok(PedidoDto.of(pedido));
        }

        return ResponseEntity.notFound().build();

    }

    @Operation(summary = "Borra un pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204 No Content",
                    description = "Se ha encontrado el pedido y se ha borrado con éxito",
                    content = @Content),
            @ApiResponse(responseCode = "404 Not Found",
                    description = "El pedido no se ha encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePedido(@PathVariable Long id){
        Pedido p= servicio.delete(id);
        if(p!=null)
            return ResponseEntity.noContent().build();

        return ResponseEntity.notFound().build();
    }



}
