package com.salesianostriana.dam.foodapi.dto.producto;

import com.salesianostriana.dam.foodapi.modelo.Producto;

public record ProductoDetailsDto(
        Long id,
        String nombre,
        String categoria
) {

    public static ProductoDetailsDto of(Producto p){
        return new ProductoDetailsDto(
                p.getId(),
                p.getNombre(),
                p.getCategoria() != null ?
                        p.getCategoria().getNombre() :
                        "Sin categoría"
        );
    }

}
