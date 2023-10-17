package com.salesianostriana.dam.foodapi.dto.cliente;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

public record ClienteListDto(
        @JsonView({PedidoBasic.class, PedidoShort.class})
        Long id,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        String nombre
) {
    public static ClienteListDto of(Cliente c){
        return new ClienteListDto(
                c.getId(),
                c.getNombre()
        );
    }
}
