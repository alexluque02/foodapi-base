package com.salesianostriana.dam.foodapi.dto.categoria;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Categoria;

public record CategoriaDto(

        Long id,
        String nombre

) {

    public static CategoriaDto of(Categoria c){
        return new CategoriaDto(
                c.getId(),
                c.getNombre()
        );
    }

}
