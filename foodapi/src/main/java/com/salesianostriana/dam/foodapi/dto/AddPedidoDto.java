package com.salesianostriana.dam.foodapi.dto;

import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.util.List;

public record AddPedidoDto(
        Long idCliente,
        List<LineaPedido> lineasPedido
) {

    public static AddPedidoDto of(Pedido p){
        return new AddPedidoDto(
                p.getCliente().getId(),
                p.getLineasPedido()
                );
    }

}
