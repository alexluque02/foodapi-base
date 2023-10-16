package com.salesianostriana.dam.foodapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Producto;
import com.salesianostriana.dam.foodapi.modelo.ProductoView.*;

import java.util.List;

public record ProductoDto(
        @JsonView({ProductoSinCategoria.class})
        Long id,
        @JsonView({ProductoSinCategoria.class})
        String nombre,
        @JsonView({ProductoSinCategoria.class})
        String imagen,
        @JsonView({ProductoSinCategoria.class})
        String descripcion,
        @JsonView({ProductoSinCategoria.class})
        double precio,
        @JsonView({ProductoSinCategoria.class})
        double precioOferta,
        @JsonView({ProductoSinCategoria.class})
        List<String> tags,
        @JsonView({ProductoCompleto.class})
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
