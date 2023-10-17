package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.salesianostriana.dam.foodapi.modelo.LineaPedido;

public record LineaPedidoDtoList(
        Long idProducto,
        int cantidad
) {

    public static LineaPedidoDtoList of(LineaPedido lp){
        return new LineaPedidoDtoList(
                lp.getProducto().getId(),
                lp.getCantidad()
        );

    }

}
