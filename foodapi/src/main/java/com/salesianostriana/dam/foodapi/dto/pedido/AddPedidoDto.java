package com.salesianostriana.dam.foodapi.dto.pedido;

import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDtoList;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.util.List;

public record AddPedidoDto(
        Long idCliente,
        List<LineaPedidoDtoList> lineasPedido
) {

    public static AddPedidoDto of(Pedido p){
        return new AddPedidoDto(
                p.getCliente().getId(),
                p.getLineasPedido().stream()
                        .map(LineaPedidoDtoList::of)
                        .toList()
                );
    }

}
