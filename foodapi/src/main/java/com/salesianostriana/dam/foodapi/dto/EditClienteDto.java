package com.salesianostriana.dam.foodapi.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.ClienteView;

public record EditClienteDto(
        Long id,
        String nombre,
        String email,
        String telefono,
        int pin
) {
    public static EditClienteDto of(Cliente c){
        return new EditClienteDto(
                c.getId(),
                c.getNombre(),
                c.getEmail(),
                c.getTelefono(),
                c.getPin()
        );
    }
}
