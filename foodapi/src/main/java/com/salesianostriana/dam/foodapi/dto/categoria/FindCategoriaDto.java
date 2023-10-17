package com.salesianostriana.dam.foodapi.dto.categoria;

import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record FindCategoriaDto (
        Long id,
        String nombre,
        int numProductos
){
    public static FindCategoriaDto of(Categoria c, int numProductos){
        return new FindCategoriaDto(
                c.getId(),
                c.getNombre(),
                numProductos
        );
    }
}
