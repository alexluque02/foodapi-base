package com.salesianostriana.dam.foodapi.dto.cliente;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDtoList(
        Long id,
        String nombre
) {
    public static ClienteDtoList of(Cliente c){
        return new ClienteDtoList(
                c.getId(),
                c.getNombre()
        );
    }
}
