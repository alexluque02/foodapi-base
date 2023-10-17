package com.salesianostriana.dam.foodapi.dto.producto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.PedidoView;
import com.salesianostriana.dam.foodapi.modelo.Producto;

public record ProductoListDto(
        @JsonView({PedidoView.PedidoBasic.class})
        Long id,
        @JsonView({PedidoView.PedidoBasic.class})
        String nombre
) {
    public static ProductoListDto of(Producto p){
        return new ProductoListDto(
                p.getId(),
                p.getNombre()
        );
    }
}
