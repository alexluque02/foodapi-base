package com.salesianostriana.dam.foodapi.dto.pedido;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.time.LocalDateTime;

public record PedidoClienteListDto(
        @JsonView({ClienteComplete.class})
        Long id,
        @JsonView({ClienteComplete.class})
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
                        .mapToDouble(lineaPedido -> lineaPedido.getCantidad()*
                                lineaPedido.getProducto().getPrecio())
                        .sum(),
                (int)p.getLineasPedido().stream()
                        .map(LineaPedido::getProducto)
                        .distinct()
                        .count(),
                (int)p.getLineasPedido().stream()
                        .mapToDouble(lineaPedido -> lineaPedido.getCantidad())
                        .sum()
        );
    }

}
