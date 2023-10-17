package com.salesianostriana.dam.foodapi.dto.pedido;

import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.cliente.ClienteDto;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.ClienteView.*;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        @JsonView({PedidoBasic.class, PedidoShort.class})
        Long id,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        LocalDateTime fecha,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        double importe,
        @JsonView({ClienteBasic.class, PedidoBasic.class, PedidoShort.class})
        ClienteDto cliente,
        @JsonView({PedidoBasic.class})
        List<LineaPedidoDto> lineasPedido,
        @JsonView({PedidoShort.class})
        int cantidadProductosDiferentes,
        @JsonView({PedidoShort.class})
        int cantidadTotal
) {

        public PedidoDto(Long id, LocalDateTime fecha, double importe, ClienteDto cliente, List<LineaPedidoDto> lineasPedido){
                this(id, fecha, importe, cliente, lineasPedido, 0, 0);
        }

        public static PedidoDto of(Pedido p){
                return new PedidoDto(
                        p.getId(),
                        LocalDateTime.now(),
                        //Sumar todos los precios de líneas de pedido
                );
        }

}
