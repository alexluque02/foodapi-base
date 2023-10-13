package com.salesianostriana.dam.foodapi.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LineaPedidoPK implements Serializable {

    private Pedido pedido;
    private Long codLinea;

    private LineaPedidoPK() { }

}
