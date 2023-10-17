package com.salesianostriana.dam.foodapi.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@IdClass(LineaPedidoPK.class)
public class LineaPedido {

    @Id
    @ManyToOne
    @ToString.Exclude
    private Pedido pedido;

    @Id
    @GeneratedValue
    private Long codLinea;

    private double precioUnitario;

    private int cantidad;

    @ManyToOne
    @ToString.Exclude
    private Producto producto;

    public LineaPedidoPK getId() { // Nos devuelve la PK completa
        return new LineaPedidoPK(pedido, codLinea);
    }

    public void setId(LineaPedidoPK pk) { // Podemos settear la PK compelta
        this.pedido = pk.getPedido();
        this.codLinea = pk.getCodLinea();
    }





}
