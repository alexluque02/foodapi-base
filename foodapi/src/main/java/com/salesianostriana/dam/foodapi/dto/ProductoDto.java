package com.salesianostriana.dam.foodapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.modelo.ProductoView;

import java.util.List;

public record ProductoDto(
        @JsonView({ProductoView.ProductoSinCategoria.class})
        Long id,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        String nombre,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        String imagen,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        String descripcion,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        double precio,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        double precioOferta,
        @JsonView({ProductoView.ProductoSinCategoria.class})
        List<String> tags,
        @JsonView({ProductoView.ProductoCompleto.class})
        String categoria
) {

        public static ProductoDto of(Producto p){
                return new ProductoDto(
                        p.getId(),
                        p.getNombre(),
                        p.getImagen(),
                        p.getDescripcion(),
                        p.getPrecio(),
                        p.getPrecioOferta(),
                        p.getTags(),
                        p.getCategoria() != null ?
                                p.getCategoria().getNombre() :
                                "Sin categoría"
                );
        }

}
