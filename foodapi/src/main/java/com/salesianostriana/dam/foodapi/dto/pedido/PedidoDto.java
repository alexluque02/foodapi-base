package com.salesianostriana.dam.foodapi.dto.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.salesianostriana.dam.foodapi.dto.cliente.ClienteListDto;
import com.salesianostriana.dam.foodapi.dto.lineaPedido.LineaPedidoDto;
import com.salesianostriana.dam.foodapi.modelo.LineaPedido;
import com.salesianostriana.dam.foodapi.modelo.Pedido;
import com.salesianostriana.dam.foodapi.modelo.PedidoView.*;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoDto(
        @JsonView({PedidoBasic.class, PedidoShort.class})
        Long id,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime fecha,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        double importe,
        @JsonView({PedidoBasic.class, PedidoShort.class})
        ClienteListDto cliente,
        @JsonView({PedidoBasic.class})
        List<LineaPedidoDto> lineasPedido,
        @JsonView({PedidoShort.class})
        int cantidadProductosDiferentes,
        @JsonView({PedidoShort.class})
        int cantidadTotal
) {

        public PedidoDto(Long id, LocalDateTime fecha, double importe, ClienteListDto cliente, List<LineaPedidoDto> lineasPedido){
                this(id, fecha, importe, cliente, lineasPedido, 0, 0);
        }

        public PedidoDto(Long id, LocalDateTime fecha, double importe, ClienteListDto cliente, int cantidadProductosDiferentes, int cantidadTotal){
                this(id, fecha, importe, cliente, null, cantidadProductosDiferentes, cantidadTotal);
        }

        public static PedidoDto of(Pedido p){
                return new PedidoDto(
                        p.getId(),
                        p.getFecha(),
                        p.getLineasPedido().stream()
                                .mapToDouble(lineaPedido -> lineaPedido.getCantidad()*
                                        lineaPedido.getProducto().getPrecio())
                                .sum(),
                        ClienteListDto.of(p.getCliente()),
                        p.getLineasPedido().stream()
                                .map(LineaPedidoDto::of)
                                .toList()
                );
        }

        public static PedidoDto ofAuxiliar(Pedido p){
                return new PedidoDto(
                        p.getId(),
                        p.getFecha(),
                        p.getLineasPedido().stream()
                                .mapToDouble(lineaPedido -> lineaPedido.getCantidad()*
                                        lineaPedido.getPrecioUnitario())
                                .sum(),
                        ClienteListDto.of(p.getCliente()),
                        (int)p.getLineasPedido().stream()
                                .map(LineaPedido::getProducto)
                                .distinct()
                                .count(),
                        (int)p.getLineasPedido().stream()
                                .mapToDouble(LineaPedido::getCantidad)
                                .sum()
                );
        }

}
