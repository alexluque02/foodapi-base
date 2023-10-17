package com.salesianostriana.dam.foodapi.dto;

public record LineaPedidoDto(
        Long codLinea,
        //Como llamar al producto con dos dto diferentes
        ProductoDto producto,
        int cantidad,
        double precioUnitario,
        double subtotal
) {
}
