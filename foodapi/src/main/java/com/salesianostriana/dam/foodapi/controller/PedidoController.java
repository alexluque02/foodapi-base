package com.salesianostriana.dam.foodapi.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.AddPedidoDto;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoDto;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.servicios.PedidoServicio;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Pedido", description = "El controlador de pedidos tiene diferentes métodos" +
        " para obtener información variada sobre los pedidos")
public class PedidoController {

    private final PedidoServicio servicio;


    @PostMapping("/")
    @JsonView({PedidoDto.class, ClienteBasic.class})
    public ResponseEntity<PedidoDto> addPedido(@RequestBody AddPedidoDto nuevo){
        Pedido p = servicio.add(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoDto.of(p));
    }

}
