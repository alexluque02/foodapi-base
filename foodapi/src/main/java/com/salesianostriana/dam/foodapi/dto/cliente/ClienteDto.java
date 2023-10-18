package com.salesianostriana.dam.foodapi.dto.cliente;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.pedido.PedidoClienteListDto;
import com.salesianostriana.dam.foodapi.modelo.Cliente;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.util.List;

public record ClienteDto(
        @JsonView({ClienteBasic.class, ClienteComplete.class})
        Long id,
        @JsonView({ClienteBasic.class, ClienteComplete.class})
        String nombre,
        @JsonView({ClienteSinPin.class, ClienteComplete.class})
        String email,
        @JsonView({ClienteSinPin.class, ClienteComplete.class})
        String telefono,
        @JsonView({ClienteList.class, ClienteComplete.class})
        int pin,
        @JsonView({ClienteDetails.class})
        int numeroPedidos,
        @JsonView({ClienteComplete.class})
        List<PedidoClienteListDto> pedidos


) {

    public static ClienteDto of(Cliente c){
        return new ClienteDto(
                c.getId(),
                c.getNombre(),
                c.getEmail(),
                c.getTelefono(),
                c.getPin(),
                c.getPedidos().size(),
                c.getPedidos().stream()
                        .map(PedidoClienteListDto::of)
                        .toList()
        );
    }

}
