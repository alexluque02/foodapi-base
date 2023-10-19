package com.salesianostriana.dam.foodapi.dto.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salesianostriana.dam.foodapi.dto.cliente.ClienteDetailsDto;
import com.salesianostriana.dam.foodapi.dto.cliente.ClienteListDto;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDetailsDto;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDetailsDto(
        Long id,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime fecha,
        double importe,
        ClienteDetailsDto cliente,
        List<LineaPedidoDetailsDto> lineasPedido
) {

        public static PedidoDetailsDto of(Pedido p){
                return new PedidoDetailsDto(
                        p.getId(),
                        p.getFecha(),
                        p.getLineasPedido().stream()
                                .mapToDouble(LineaPedidoDto::getSubtotal)
                                .sum(),
                        ClienteDetailsDto.of(p.getCliente()),
                        p.getLineasPedido().stream()
                                .map(LineaPedidoDetailsDto::of)
                                .toList()

                );
        }

}
