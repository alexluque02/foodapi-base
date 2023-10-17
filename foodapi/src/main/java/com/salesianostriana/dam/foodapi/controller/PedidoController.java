package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;
import com.salesianostriana.dam.foodapi.servicios.PedidoServicio;
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


    @PostMapping("/")
    @JsonView({PedidoBasic.class})
    public ResponseEntity<PedidoDto> addPedido(@RequestBody AddPedidoDto nuevo){
        Pedido p = servicio.add(nuevo);
        System.out.println();
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoDto.of(p));
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
