package com.salesianostriana.dam.foodapi.dto.cliente;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

public record ClienteDtoList(
        @JsonView({PedidoBasic.class, PedidoShort.class})
        Long id,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        String nombre
) {
    public static ClienteDtoList of(Cliente c){
        return new ClienteDtoList(
                c.getId(),
                c.getNombre()
        );
    }
}
