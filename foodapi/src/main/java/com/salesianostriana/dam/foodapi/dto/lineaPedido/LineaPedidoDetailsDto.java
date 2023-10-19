package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoDetailsDto;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoListDto;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView;

public record LineaPedidoDetailsDto(
        Long codLinea,
        ProductoDetailsDto producto,
        int cantidad,
        double precioUnitario,
        double subtotal
) {

    public static LineaPedidoDetailsDto of(LineaPedido lp){
        return new LineaPedidoDetailsDto(
                lp.getCodLinea(),
                ProductoDetailsDto.of(lp.getProducto()),
                lp.getCantidad(),
                LineaPedidoDto.getprecioUnitario(lp),
                LineaPedidoDto.getSubtotal(lp)
        );
    }

}
