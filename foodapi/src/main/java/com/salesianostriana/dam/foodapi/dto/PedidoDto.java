package com.salesianostriana.dam.foodapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        @JsonView({PedidoBasic.class})
        Long id,
        @JsonView({PedidoBasic.class})
        LocalDateTime fecha,
        @JsonView({PedidoBasic.class})
        double importe,
        @JsonView({ClienteBasic.class, PedidoBasic.class})
        ClienteDto clienteBasic,
        @JsonView({ClienteSinPin.class})
        ClienteDto cliente2SinPin,
        @JsonView({PedidoBasic.class})
        List<LineaPedido> lineasPedido,
        int cantidadProductosDiferentes,
        int cantidadTotal
) {
}
