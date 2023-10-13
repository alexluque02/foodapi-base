package com.salesianostriana.dam.foodapi.dto;

import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record CategoriaDto(
        Long id,
        String nombre
) {

    public CategoriaDto of (Categoria c){
        return new CategoriaDto(
                c.getId(),
                c.getNombre()
        );
    }

}
