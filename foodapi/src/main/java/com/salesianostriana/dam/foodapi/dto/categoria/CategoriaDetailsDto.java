package com.salesianostriana.dam.foodapi.dto.categoria;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record CategoriaDetailsDto(
        Long id,
        String nombre,
        int numProductos
) {

    public static CategoriaDetailsDto of(Categoria c, int numProductos){
        return new CategoriaDetailsDto(
                c.getId(),
                c.getNombre(),
                numProductos
        );
    }

}
