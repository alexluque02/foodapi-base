package com.salesianostriana.dam.foodapi.dto;

import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record EditCategoriaDto(
        Long id,
        String nombre
) {

    public EditCategoriaDto of (Categoria c){
        return new EditCategoriaDto(
                c.getId(),
                c.getNombre()
        );
    }

}
