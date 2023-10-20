package com.salesianostriana.dam.foodapi.dto.producto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.categoria.CategoriaDto;
import com.salesianostriana.dam.foodapi.modelo.Producto;

import java.util.List;

public record FindProductoDto(
        Long id,
        String nombre,
        String imagen,
        String descripcion,
        double precio,
        double precioOferta,
        List<String> tags,
        CategoriaDto categoria
) {

    public static FindProductoDto of(Producto p){
        return new FindProductoDto(
                p.getId(),
                p.getNombre(),
                p.getImagen(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getPrecioOferta(),
                p.getTags(),
                CategoriaDto.of(p.getCategoria())
        );
    }

}
