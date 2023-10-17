package com.salesianostriana.dam.foodapi.dto.cliente;

import com.salesianostriana.dam.foodapi.modelo.Cliente;

public record ClienteDetailsDto(
        Long id,
        String nombre,
        String email,
        String telefono
) {

    public static ClienteDetailsDto of(Cliente c){
        return new ClienteDetailsDto(
                c.getId(),
                c.getNombre(),
                c.getEmail(),
                c.getTelefono()
        );
    }

}
