package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoDto;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoListDto;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
@JsonInclude(JsonInclude.Include.NON_NULL)
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
