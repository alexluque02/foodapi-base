package com.salesianostriana.dam.foodapi.dto.lineaPedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoDto;
import com.salesianostriana.dam.foodapi.dto.producto.ProductoListDto;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

public record LineaPedidoDto(
        @JsonView({PedidoBasic.class})
        Long codLinea,
        @JsonView({PedidoBasic.class})
        ProductoListDto producto,
        @JsonView({PedidoBasic.class})
        int cantidad,
        @JsonView({PedidoBasic.class})
        double precioUnitario,
        @JsonView({PedidoBasic.class})
        double subtotal
) {
    public static LineaPedidoDto of(LineaPedido lp){
        return new LineaPedidoDto(
                lp.getCodLinea(),
                ProductoListDto.of(lp.getProducto()),
                lp.getCantidad(),
                getprecioUnitario(lp),
                getSubtotal(lp)
        );
    }

    public static double getprecioUnitario(LineaPedido lp){
        return lp.getProducto().getPrecioOferta()>0?
                lp.getProducto().getPrecioOferta() :
                lp.getProducto().getPrecio();
    }

    public static double getSubtotal(LineaPedido lp){
        return lp.getProducto().getPrecioOferta()>0?
                lp.getProducto().getPrecioOferta()* lp.getCantidad() :
                lp.getProducto().getPrecio()*lp.getCantidad();
    }
}
