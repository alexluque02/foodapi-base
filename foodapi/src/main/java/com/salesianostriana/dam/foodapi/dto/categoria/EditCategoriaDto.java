package com.salesianostriana.dam.foodapi.dto.categoria;

import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record EditCategoriaDto(
        String nombre
) {

    public static EditCategoriaDto of(Categoria c){
        return new EditCategoriaDto(
                c.getNombre()
        );
    }

}
