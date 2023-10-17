package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.salesianostriana.dam.foodapi.dto.producto.ProductoDto;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoListDto;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;

public record LineaPedidoDto(
        Long codLinea,
        //Como llamar al producto con dos dto diferentes
        ProductoListDto producto,
        int cantidad,
        double precioUnitario,
        double subtotal
) {
    public static LineaPedidoDto of(LineaPedido lp){
        return new LineaPedidoDto(
                lp.getCodLinea(),
                ProductoListDto.of(lp.getProducto()),
                lp.getCantidad(),
                lp.getProducto().getPrecio(),
                lp.getProducto().getPrecio()*lp.getCantidad()
        );
    }
}
