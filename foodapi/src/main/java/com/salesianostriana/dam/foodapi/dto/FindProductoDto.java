package com.salesianostriana.dam.foodapi.dto;

import com.salesianostriana.dam.foodapi.modelo.Categoria;
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
        Categoria categoria
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
                p.getCategoria()
        );
    }

}
