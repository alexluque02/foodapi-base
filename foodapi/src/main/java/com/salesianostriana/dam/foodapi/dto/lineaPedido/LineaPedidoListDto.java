package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.salesianostriana.dam.foodapi.modelo.LineaPedido;

public record LineaPedidoListDto(
        Long idProducto,
        int cantidad
) {

    public static LineaPedidoListDto of(LineaPedido lp){
        return new LineaPedidoListDto(
                lp.getProducto().getId(),
                lp.getCantidad()
        );

    }

}
