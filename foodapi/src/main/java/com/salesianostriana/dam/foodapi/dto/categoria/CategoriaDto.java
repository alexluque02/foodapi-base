package com.salesianostriana.dam.foodapi.dto.categoria;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Categoria;
import com.salesianostriana.dam.foodapi.modelo.CategoriaView.*;

public record CategoriaDto(
        @JsonView({CategoriaList.class})
        Long id,
        @JsonView({CategoriaList.class})
        String nombre,
        @JsonView({CategoriaDetails.class})
        int numProductos
) {

    public CategoriaDto (Long id, String nombre){
        this(id, nombre, 0);
    }

    public static CategoriaDto of(Categoria c){
        return new CategoriaDto(
                c.getId(),
                c.getNombre()
        );
    }

    public static CategoriaDto of(Categoria c, int numProductos){
        return new CategoriaDto(
                c.getId(),
                c.getNombre(),
                numProductos
        );
    }

}
