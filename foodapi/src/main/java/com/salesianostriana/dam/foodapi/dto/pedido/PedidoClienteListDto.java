package com.salesianostriana.dam.foodapi.dto.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.time.LocalDateTime;

public record PedidoClienteListDto(
        @JsonView({ClienteComplete.class})
        Long id,
        @JsonView({ClienteComplete.class})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime fecha,
        @JsonView({ClienteComplete.class})
        double importe,
        @JsonView({ClienteComplete.class})
        int cantidadProductosDiferentes,
        @JsonView({ClienteComplete.class})
        int cantidadProductos
) {

    public static PedidoClienteListDto of(Pedido p){
        return new PedidoClienteListDto(
                p.getId(),
                p.getFecha(),
                p.getLineasPedido().stream()
                        .mapToDouble(LineaPedidoDto::getSubtotal)
                        .sum(),
                (int)p.getLineasPedido().stream()
                        .map(LineaPedido::getProducto)
                        .distinct()
                        .count(),
                (int)p.getLineasPedido().stream()
                        .mapToDouble(LineaPedido::getCantidad)
                        .sum()
        );
    }

}
