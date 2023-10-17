package com.salesianostriana.dam.foodapi.dto.producto;

import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Producto;

public record ProductoListDto(
        Long id,
        String nombre
) {
    public static ProductoListDto of(Producto p){
        return new ProductoListDto(
                p.getId(),
                p.getNombre()
        );
    }
}
