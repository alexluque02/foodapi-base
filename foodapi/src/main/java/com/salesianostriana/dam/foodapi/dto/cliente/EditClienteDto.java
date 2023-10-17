package com.salesianostriana.dam.foodapi.dto.cliente;

import com.salesianostriana.dam.foodapi.modelo.Cliente;

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
