package com.salesianostriana.dam.foodapi.dto.producto;

import com.salesianostriana.dam.foodapi.modelo.Producto;

import java.util.List;

public record EditProductoDto(

        String nombre,
        String imagen,
        String descripcion,
        double precio,
        double precioOferta,
        List<String> tags,
        Long categoria
) {

    public static EditProductoDto of(Producto p){
        return new EditProductoDto(
                p.getNombre(),
                p.getImagen(),
                p.getDescripcion(),
                p.getPrecio(),
                p.getPrecioOferta(),
                p.getTags(),
                p.getCategoria() != null ?
                        p.getCategoria().getId() :
                        0
        );
    }

}
